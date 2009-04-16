/*
 * Copyright 2009 Roam - roam.be
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.roam.hue.doj;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kevin
 */
public enum MatchType {

    EXISTING() {
        
        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value);
        }
        
    },
    
    MATCHING() {

        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value) && value.equals(valueToLookFor);
        }

    },

    CONTAINED_WITH_WHITESPACE() {

        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value) && (" " + value + " ").contains(" " + valueToLookFor + " ");
        }

    },

    STARTING_WITH() {

        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value) && value.startsWith(valueToLookFor);
        }

    },

    ENDING_WITH() {

        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value) && value.endsWith(valueToLookFor);
        }

    },

    CONTAINING() {

        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value) && value.contains(valueToLookFor);
        }

    },

    CONTAINED_WITH_HYPHENS() {

        @Override
        public boolean isMatch(String value, String valueToLookFor) {
            return !StringUtils.isBlank(value) && ("-" + value + "-").contains("-" + valueToLookFor + "-");
        }

    };
    
    public boolean isMatch(String value, String valueToLookFor) {
        return false;
    }

}
