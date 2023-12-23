/*
 *  Copyright 2016-2023 Qameta Software OÜ
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
package ru.yandex.qatools.allure.jenkins;

import hudson.model.Job;
import hudson.model.ProminentProjectAction;
import hudson.model.Run;
import org.kohsuke.stapler.StaplerProxy;

public class AllureProgressProjectAction implements ProminentProjectAction, StaplerProxy {

    private final Job<?, ?> job;

    public AllureProgressProjectAction(final Job<?, ?> job) {
        this.job = job;
    }

    @Override
    public String getDisplayName() {
        return Messages.AllureReportPlugin_Title();
    }

    @Override
    public String getIconFileName() {
        return AllureProgressPlugin.getIconFilename();
    }

    @Override
    public String getUrlName() {
        return AllureProgressPlugin.URL_PATH;
    }

    @Override
    public Object getTarget() {
        final Run<?, ?> last = job.getLastCompletedBuild();
        return last == null ? null : last.getAction(AllureProgressBuildAction.class);
    }

    //copied from junit-plugin
    public AllureProgressBuildAction getLastAllureBuildAction() {
        final Run<?, ?> tb = job.getLastSuccessfulBuild();
        Run<?, ?> b = job.getLastBuild();
        while (b != null) {
            final AllureProgressBuildAction a = b.getAction(AllureProgressBuildAction.class);
            if (a != null && !b.isBuilding()) {
                return a;
            }
            if (b.equals(tb)) {
                // if even the last successful build didn't produce the test result,
                // that means we just don't have any tests configured.
                return null;
            }
            b = b.getPreviousBuild();
        }
        return null;
    }
}
