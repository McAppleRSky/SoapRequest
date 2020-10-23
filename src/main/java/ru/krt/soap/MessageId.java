package ru.krt.soap;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.UUID;

public class MessageId {

    String currentDateValue = null;

    long timestamp, nodeIdentifier, clockSequence, msb, lsb;

    static Random random = new Random();

    public String generate() {

        class NestedTimestampComponent{

            private  long getGregorianMilliseconds() {
                return LocalDate.parse("1582-10-15").atStartOfDay(ZoneId.of("UTC")).toInstant().getEpochSecond()
                        * MILLISECONDS_PER_SECOND;
            }

            public  final long GREGORIAN_MILLISECONDS = getGregorianMilliseconds();

            public static final long MILLISECONDS_PER_SECOND = 1_000L;
            public static final long TIMESTAMP_RESOLUTION = 10_000L;

            public  long toTimestamp(final long unixMilliseconds) {
                return (unixMilliseconds - GREGORIAN_MILLISECONDS) * TIMESTAMP_RESOLUTION;
            }

            public long getCurrentTimestamp() {
                long result;
                result = toTimestamp(System.currentTimeMillis());
                if (currentDateValue != null){
                    result = LocalDate.parse(currentDateValue).atStartOfDay(ZoneId.of("UTC")).toInstant().getEpochSecond()
                            * MILLISECONDS_PER_SECOND;
                }
                return result;
            }

            protected long previousTimestamp = 0;
            protected static final int COUNTER_MIN = 0;
            protected static final int COUNTER_MAX = 9_999;
            protected static final int COUNTER_OFFSET_MAX = 0xff;

            protected long value;
            public final long minValue;
            public final long maxValue;

            public NestedTimestampComponent() {
                this.minValue = COUNTER_MIN;
                this.maxValue = COUNTER_MAX;
                this.value = minValue;
                this.value = random.nextInt(0xff);
            }

            public long current() {
                return this.value;
            }

            public void reset() {
                this.value = this.value & COUNTER_OFFSET_MAX;
            }

            public long next() {
                if (this.value >= maxValue) {
                    this.value = minValue;
                    return this.value;
                }
                return ++this.value;
            }

            protected long getNextCounter(final long timestamp) {
                if (timestamp > this.previousTimestamp) {
                    this.reset();
                }
                this.previousTimestamp = timestamp;
                return this.next();
            }

            long getTimestamp(){
                long timestamp = getCurrentTimestamp();
                long counter = getNextCounter(timestamp);
                // (4a) simulate a high resolution timestamp
                return timestamp + counter;
            }
        }

        class NestedNodeidentComponent{


            public long getNodeIdentifier1() {
                //String value = getProperty(PROPERTY_NODEID);
                /**
                 *                 if (value == null) {
                 *                     return 0;
                 *                 }
                 *                                 return //toNumber(value) &
                 *                         0x0000FFFFFFFFFFFFL;
                 */
                return 0;
            }

            public  long toNumber(final byte[] bytes, final int start, final int end) {
                long result = 0;
                for (int i = start; i < end; i++) {
                    result = (result << 8) | (bytes[i] & 0xff);
                }
                return result;
            }

            public  long toNumber(final byte[] bytes) {
                return toNumber(bytes, 0, bytes.length);
            }

            public  long nextLong() {
                byte[] bytes = new byte[8];
                random.nextBytes(bytes);
                return toNumber(bytes);
            }

            public  long setMulticastNodeIdentifier(long nodeIdentifier) {
                return nodeIdentifier | 0x0000010000000000L;
            }

            protected  long getRandomNodeIdentifier() {
                return setMulticastNodeIdentifier(nextLong());
            }

            public NestedNodeidentComponent() {
                final long preferedNodeIdentifier = getNodeIdentifier1();
                if (preferedNodeIdentifier != 0) {
                    this.nodeIdentifier1 = preferedNodeIdentifier;
                } else {
                    this.nodeIdentifier1 = getRandomNodeIdentifier();
                }
            }

            protected long nodeIdentifier1;

            public long getNodeIdentifier() {
                return this.nodeIdentifier1;
            }
        }

        // (3a) get the timestamp
        NestedTimestampComponent timestampComponent = new NestedTimestampComponent();
        timestamp = timestampComponent.getTimestamp();

        // (4a)(5a) get the node identifier
        NestedNodeidentComponent nodeidentComponent = new NestedNodeidentComponent();
        nodeIdentifier = nodeidentComponent.getNodeIdentifier();


        // (5a)(6a) get the sequence value
        if (timestamp > timestampComponent.previousTimestamp) {
            timestampComponent.previousTimestamp = timestamp;
            clockSequence = timestampComponent.current();
        }else {
            timestampComponent.previousTimestamp = timestamp;
            clockSequence = timestampComponent.next();
        }

        // (9a) format the most significant bits
        msb =  ((timestamp & 0x0fff_0000_00000000L) >>> 48)
                | ((timestamp & 0x0000_ffff_00000000L) >>> 16)
                | ((timestamp & 0x0000_0000_ffffffffL) << 32)
                | 0x00000000_0000_1000L;

        // (9a) format the least significant bits
        lsb =  ((clockSequence << 48) | (nodeIdentifier & 0x0000ffffffffffffL) | 0x8000000000000000L);

        // (9a) format a UUID from the MSB and LSB
        UUID uuid = new UUID(msb, lsb);
        return uuid.toString();
    }



}
