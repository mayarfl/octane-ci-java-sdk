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

package com.hp.octane.integrations.services.pullrequests;

import com.hp.octane.integrations.OctaneSDK;
import com.hp.octane.integrations.dto.scm.PullRequest;
import com.hp.octane.integrations.services.pullrequests.factory.FetchParameters;
import com.hp.octane.integrations.services.rest.RestService;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;


public interface PullRequestService {

    /**
     * Service instance producer - for internal usage only (protected by inaccessible configurer)
     *
     * @param configurer  SDK services configurer object
     * @param restService Rest Service
     * @return initialized service
     */
    static PullRequestService newInstance(OctaneSDK.SDKServicesConfigurer configurer, RestService restService) {
        return new PullRequestServiceImpl(configurer, restService);
    }

    void sendPullRequests(List<PullRequest> pullRequests, String workspaceId, FetchParameters fetchParameters, Consumer<String> logConsumer) throws IOException;

    long getLastUpdateTime(String workspaceId, String repoUrl);
}
