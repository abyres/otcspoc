
package net.abyres.tm.otcs.model;

import javax.persistence.AttributeConverter;

/**
 *
 * @author onn
 */
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return (value != null && value) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "Y".equals(value);
    }

}
