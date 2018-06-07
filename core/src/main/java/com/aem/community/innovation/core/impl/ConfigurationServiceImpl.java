package com.aem.community.innovation.core.impl;
import java.util.Map;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aem.community.innovation.core.ConfigurationService;
 
@Service({ConfigurationServiceImpl.class})
@Component(immediate=true, metatype=true, label="Example Configuration Service")
public class ConfigurationServiceImpl
  implements ConfigurationService
{
  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);
  private static final String CLASS_NAME = "[ConfigurationService]: ";
  @Property(unbounded=PropertyUnbounded.ARRAY, label="Multi String", cardinality=50, description="Example for Multi field config")
  private static final String MULTI_FIELD = "multifield";
  @Property(unbounded=PropertyUnbounded.DEFAULT, label="Simple String", description="Example for Simple text field config")
  private static final String SIMPLE_FIELD = "simplefield";
  private String[] multiString;
  private String simpleString;
   
  public String[] getMultiString()
  {
    return this.multiString;
  }
   
  public String getSimpleString()
  {
    return this.simpleString;
  }
   
  @Activate
  protected void activate(Map<String, Object> properties)
  {
    LOG.info("[*** AEM ConfigurationService]: activating configuration service");
    readProperties(properties);
  }
   
  protected void readProperties(Map<String, Object> properties)
  {
    LOG.info(properties.toString());
    this.multiString = PropertiesUtil.toStringArray(properties.get("multifield"));
    LOG.info("Mutli String Size: " + this.multiString.length);
    this.simpleString = PropertiesUtil.toString(properties.get("simplefield"), "default");
    LOG.info("Simple String: " + this.simpleString);
  }
}
