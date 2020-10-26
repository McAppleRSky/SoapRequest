<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
>

    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!--
      <xsl:template match="/*">
        <xsl:element name="soap:{local-name()}" namespace="http://schemas.xmlsoap.org/soap/envelope/">
          <xsl:copy-of select="namespace::*"/>
          <xsl:apply-templates select="@*|node()"/>
        </xsl:element>
      </xsl:template>
    -->

    <!--  <xsl:template match="/*">
        <xsl:element name="soap:{local-name()}">
          <xsl:copy-of select="namespace::*" />
          <xsl:apply-templates select="@* | node()" />
        </xsl:element>
      </xsl:template>-->

    <!--<xsl:template match="">
        template
        <xsl:copy>
          <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
      </xsl:template>
    -->


    <!--<xsl:template match="envsoap " xmlns:envsoap="http://schemas.xmlsoap.org/soap/envelope/">
      <xsl:element name="soap:{local-name()}" namespace="{namespace-uri(.)}">
        <xsl:apply-templates select="@*|node()"/>
      </xsl:element>
    </xsl:template>-->

    <!-- <xsl:param name="use-this-prefix"/> -->

    <!-- <xsl:template match="envsoap:*" xmlns:envsoap="http://schemas.xmlsoap.org/soap/envelope/">
      <xsl:element name="soap:{local-name()}" namespace="{namespace-uri(.)}">
        <xsl:apply-templates select="@*|node()"/>
      </xsl:element>
    </xsl:template> -->
    <!-- <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="urn:interface/Employee/Data/Update/v1" xmlns:v11="urn:Employee/Data/Update/v1.0"> -->

</xsl:stylesheet>
