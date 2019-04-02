package etc.qa.at.utils;

import cucumber.runtime.java.guice.ScenarioScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@ScenarioScoped
public class ScopedData {
    Logger logger = LogManager.getLogger(this);

    public HashMap<String, HashMap<Class<?>, Object>> attributesMap = new HashMap<>();

    public void putAttributeValue(String attributeLogicalName, Class<?> type, Object value) {
        HashMap<Class<?>, Object> valueMap = new HashMap<>(1);
        valueMap.put(type, value);
        if (this.attributesMap.containsKey(attributeLogicalName)) {
            logger.warn("ScopedData already contains the attribute : " + attributeLogicalName + " and is being overriden from " + this.attributesMap.get(attributeLogicalName) + " to value : " + value);
        }
        attributesMap.put(attributeLogicalName, valueMap);
    }

    public Object getAttributeValue(String attributeName) {
        if (attributesMap.get(attributeName) == null)
            logger.warn("No value present in scopedData for the attribute : " + attributeName);
        if (attributesMap.get(attributeName).values().size() < 1 || attributesMap.get(attributeName).values().size() > 1) {
            logger.warn("ScopedData fetch for the attribute : " + attributeName + " has (" + attributesMap.get(attributeName).values().size() + ") values");
            return null;
        }
        return attributesMap.get(attributeName).values().toArray()[0];
    }
}
