/*
 * ecoCode Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@ecocode.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.ecocode.java.checks.environment.bottleneck;

import io.ecocode.java.checks.helpers.OpeningClosingMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check that WifiManager.MulticastLock#release() method is closed after WifiManager.MulticastLock#acquire().
 *
 * @see OpeningClosingMethodCheck
 */
@Rule(key = "EC503")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "EBOT002")
public class WifiMulticastLockRule extends OpeningClosingMethodCheck {
    private static final String ERROR_MESSAGE = "Failing to call WifiManager.MulticastLock#release() can cause a noticeable battery drain.";

    public WifiMulticastLockRule() {
        super("acquire", "release", "android.net.wifi.WifiManager$MulticastLock");
    }

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
