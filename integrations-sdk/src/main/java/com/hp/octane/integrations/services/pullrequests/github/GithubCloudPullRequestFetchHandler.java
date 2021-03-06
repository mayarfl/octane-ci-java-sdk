/*
 *     Copyright 2017 EntIT Software LLC, a Micro Focus company, L.P.
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.hp.octane.integrations.services.pullrequests.github;


import com.hp.octane.integrations.services.pullrequests.rest.authentication.AuthenticationStrategy;

import java.util.Arrays;
import java.util.List;

public class GithubCloudPullRequestFetchHandler extends GithubV3PullRequestFetchHandler {


    public GithubCloudPullRequestFetchHandler(AuthenticationStrategy authenticationStrategy) {
        super(authenticationStrategy);
    }

    @Override
    public String getRepoApiPath(String repoHttpCloneUrl) {
        validateHttpCloneUrl(repoHttpCloneUrl);

        String cloudServicePrefix = "https://github.com/";
        //"https://github.com/jenkinsci/hpe-application-automation-tools-plugin.git"; =>https://api.github.com/repos/jenkinsci/hpe-application-automation-tools-plugin
        List<String> parts = Arrays.asList(repoHttpCloneUrl.trim().substring(cloudServicePrefix.length()).split("/"));
        if (parts.size() != 2) {
            throw new IllegalArgumentException("Unexpected github cloud repository URL : " + repoHttpCloneUrl + ". Expected format : https://github.com/<user_name>/<repo_name>.git");
        }

        String user = parts.get(parts.size() - 2);
        String repoName = parts.get(parts.size() - 1);
        repoName = repoName.substring(0, repoName.length() - ".git".length());
        return String.format("https://api.github.com/repos/%s/%s", user, repoName);
    }
}
