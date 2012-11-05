<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html"/>
<xsl:template match="//course_list">
<html>
<H1>Courses</H1>
<xsl:apply-templates select="course"/>
</html>
</xsl:template>
<xsl:template name="course" match="course">
<H2><xsl:value-of select="./title"/> - <xsl:value-of select="@id"/> </H2>
<p><xsl:value-of select="./credits"/> ECTS</p>
<p><xsl:value-of select="./level"/></p>
<p><xsl:value-of select="./restrictions"/></p>
<xsl:apply-templates select="sections"/>
</xsl:template>
<xsl:template name="sections" match="sections">
<H3>Sections</H3>
<p><xsl:apply-templates select="section"/></p>
</xsl:template>
<xsl:template name="section" match="section">
<H4><xsl:value-of select="@id"/></H4>
<p><xsl:value-of select="./days"/></p>
<p><xsl:value-of select="./time_start"/></p>
<p><xsl:value-of select="./time_end"/></p>
<p><xsl:value-of select="./instructor"/></p>
<p><xsl:value-of select="./comment"/></p>
</xsl:template>
</xsl:stylesheet>
<% response.setContentType("text/xsl"); %>