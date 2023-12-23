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

import hudson.Util;
import hudson.model.Action;
import hudson.model.BuildBadgeAction;
import hudson.model.Job;
import hudson.model.Run;
import jenkins.model.RunAction2;
import jenkins.tasks.SimpleBuildStep;

import java.util.Collection;
import java.util.Collections;

public class AllureProgressBuildAction implements BuildBadgeAction, RunAction2, SimpleBuildStep.LastBuildAction {

    private static final String ALLURE_REPORT = "allure-report";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String WAS_ATTACHED_TO_BOTH = "%s was attached to both %s and %s";
    private Run<?, ?> run;

    public String getBuildNumber() {
        return run.getId();
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
    public Collection<? extends Action> getProjectActions() {
        final Job<?, ?> job = run.getParent();
        if (/* getAction(Class) and getAllActions() produces a StackOverflowError */
                !Util.filter(job.getActions(), AllureProgressProjectAction.class).isEmpty()) {
            // JENKINS-26077: someone like XUnitPublisher already added one
            return Collections.emptySet();
        }
        return Collections.singleton(new AllureProgressProjectAction(job));
    }

    @Override
    public void onAttached(final Run<?, ?> r) {
        run = r;
    }

    @Override
    public void onLoad(final Run<?, ?> r) {
        run = r;
    }
}
