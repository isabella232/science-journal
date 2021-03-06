/*
 *  Copyright 2017 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.google.android.apps.forscience.whistlepunk.filemetadata;

import androidx.annotation.VisibleForTesting;
import com.google.android.apps.forscience.whistlepunk.LabelValuePojo;
import com.google.android.apps.forscience.whistlepunk.metadata.GoosciLabelValue.LabelValue.ValueType;

/**
 * A label auto-generated by a Sensor Trigger. This has some editable custom text, as well as
 * non-editable auto-generated text that is specific to the trigger which was fired.
 */
@Deprecated
public class SensorTriggerLabelValue extends LabelValue {
  private static final String KEY_CUSTOM_STRING = "custom";
  private static final String KEY_AUTOGEN_STRING = "auto";
  private static final String KEY_SENSOR_ID = "sensorId";

  public SensorTriggerLabelValue(LabelValuePojo value) {
    super(value);
    this.value.setType(ValueType.SENSOR_TRIGGER);
  }

  @Override
  public boolean canEditTimestamp() {
    // Autogenerated label has uneditable timestamp.
    return false;
  }

  // The custom text within the SensorTriggerLabelValue is no longer used.
  @Deprecated
  public void setCustomText(String text) {
    LabelValuePojo value = getValue();
    if (value == null) {
      value = new LabelValuePojo();
      value.setType(ValueType.SENSOR_TRIGGER);
      setValue(value);
    }
    value.putData(KEY_CUSTOM_STRING, text);
  }

  // The custom text within the SensorTriggerLabelValue is no longer used.
  @Deprecated
  public String getCustomText() {
    return getCustomText(getValue());
  }

  public static String getCustomText(LabelValuePojo value) {
    return value.getDataOrDefault(KEY_CUSTOM_STRING, "");
  }

  public static void clearCustomText(LabelValuePojo value) {
    value.putData(KEY_CUSTOM_STRING, "");
  }

  public String getAutogenText() {
    return getAutogenText(getValue());
  }

  public static String getAutogenText(LabelValuePojo value) {
    return value.getDataOrDefault(KEY_AUTOGEN_STRING, "");
  }

  public String getSensorId() {
    return getSensorId(getValue());
  }

  public static String getSensorId(LabelValuePojo value) {
    return value.getDataOrDefault(KEY_SENSOR_ID, "");
  }

  @VisibleForTesting
  static void populateLabelValue(LabelValuePojo value, SensorTrigger trigger, String noteText) {
    value.setType(ValueType.SENSOR_TRIGGER);
    value.putData(KEY_CUSTOM_STRING, trigger.getNoteText());
    value.putData(KEY_AUTOGEN_STRING, noteText);
    value.putData(KEY_SENSOR_ID, trigger.getSensorId());
  }
}
