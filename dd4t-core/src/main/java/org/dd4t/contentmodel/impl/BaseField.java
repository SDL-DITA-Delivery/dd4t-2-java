package org.dd4t.contentmodel.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.dd4t.contentmodel.*;
import org.dd4t.core.serializers.impl.json.FieldTypeConverter;
import org.dd4t.core.serializers.impl.json.TridionJsonFieldTypeIdResolver;

import java.util.LinkedList;
import java.util.List;

// Note that Value is basically always a Map.get(0)
@JsonIgnoreProperties({"Value"})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "FieldType",
        visible = true)

@JsonTypeIdResolver(TridionJsonFieldTypeIdResolver.class)
public abstract class BaseField implements Field {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Values")
    private List<String> textValues;

    @JsonProperty("NumericValues")
    private List<Double> numericValues;

    @JsonProperty("DateTimeValues")
    private List<String> dateValues;

    @JsonProperty("LinkedComponentValues")
    @JsonDeserialize(contentAs = ComponentImpl.class)
    private List<Component> componentLinkValues;

    @JsonProperty("Keywords")
    @JsonDeserialize(contentAs = KeywordImpl.class)
    private List<Keyword> keywordValues;

    @JsonProperty("EmbeddedValues")
    @JsonDeserialize(contentAs = FieldSetImpl.class)
    private List<FieldSet> embeddedValues;

    @JsonProperty("FieldType")
    @JsonDeserialize(converter = FieldTypeConverter.class)
    private FieldType fieldType;

    @JsonProperty("XPath")
    private String xPath;

    /**
     * Get the values of the field.
     *
     * @return a list of objects, where the type is depending of the field type.
     * Never returns null.
     */
    public abstract List<Object> getValues();

    /**
     * Get the numeric field values
     *
     * @return numericValues
     */
    public List<Double> getNumericValues() {
        return numericValues == null ? new LinkedList<Double>() : numericValues;
    }

    /**
     * Set the numeric field values
     *
     * @param numericValues
     */
    public void setNumericValues(List<Double> numericValues) {
        this.numericValues = numericValues;
    }

    /**
     * Get the date time values
     *
     * @return
     */
    public List<String> getDateTimeValues() {
        return dateValues == null ? new LinkedList<String>() : dateValues;
    }

    /**
     * Set the date time field values
     *
     * @param dateTimeValues
     */
    public void setDateTimeValues(List<String> dateTimeValues) {
        this.dateValues = dateTimeValues;
    }

    /**
     * Get the linked component values
     *
     * @return
     */
    public List<Component> getLinkedComponentValues() {
        return componentLinkValues != null ? componentLinkValues : new LinkedList<Component>();
    }

    /**
     * Set the linked component field values
     *
     * @param linkedComponentValues
     */
    public void setLinkedComponentValues(List<Component> linkedComponentValues) {
        this.componentLinkValues = linkedComponentValues;
    }

    /**
     * Get the name of the field.
     *
     * @return the name of the field
     */
    public String getName() {
        return name == null ? "" : name;
    }

    /**
     * Set the name of the field
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get xPath value for the field
     *
     * @return
     */
    @Override
    public String getXPath() {
        return xPath;
    }

    /**
     * Set xPath value for the field
     *
     * @param xPath
     */
    @Override
    public void setXPath(String xPath) {
        this.xPath = xPath;
    }

    /**
     * Get the field type
     *
     * @return the field type
     */
    public FieldType getFieldType() {
        return fieldType;
    }

    /**
     * Set the field type
     *
     * @param fieldType
     */
    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * Get the text field values
     *
     * @return a list of text values
     */
    public List<String> getTextValues() {
        return textValues == null ? new LinkedList<String>() : textValues;
    }

    /**
     * Set the text field values
     *
     * @param textValues
     */

    public void setTextValues(List<String> textValues) {
        this.textValues = textValues;
    }

    /**
     * Get the embedded values
     *
     * @return the embedded values as a map
     */
    public List<FieldSet> getEmbeddedValues() {
        return embeddedValues != null ? embeddedValues : new LinkedList<FieldSet>();
    }

    /**
     * Set the embedded values
     *
     * @param embeddedValues
     */
    public void setEmbeddedValues(List<FieldSet> embeddedValues) {
        this.embeddedValues = embeddedValues;
    }

    public List<Keyword> getKeywordValues() {
        return keywordValues != null ? keywordValues : new LinkedList<Keyword>();
    }

    public void setKeywordvalues(List<Keyword> keywordValues) {
        this.keywordValues = keywordValues;
    }
}