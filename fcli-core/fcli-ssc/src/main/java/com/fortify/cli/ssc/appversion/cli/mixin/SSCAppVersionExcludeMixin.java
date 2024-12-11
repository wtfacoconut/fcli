package com.fortify.cli.ssc.appversion.cli.mixin;

import java.util.Set;

import com.fortify.cli.common.rest.unirest.IHttpRequestUpdater;
import com.fortify.cli.common.util.DisableTest;
import com.fortify.cli.common.util.DisableTest.TestType;

import kong.unirest.HttpRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine.Option;

public class SSCAppVersionExcludeMixin implements IHttpRequestUpdater {
    @DisableTest(TestType.MULTI_OPT_PLURAL_NAME)
    @Option(names = {"--exclude", "-e"}, split = ",", descriptionKey = "fcli.ssc.appversion.list.exclude", paramLabel="<values>")
    private Set<SSCAppVersionExclude> excludes;

    public HttpRequest<?> updateRequest(HttpRequest<?> request) {
        if ( excludes!=null ) {
            for ( var exclude : excludes) {
                var queryParameterName = exclude.getRequestParameterName();
                if ( queryParameterName!=null ) {
                    request = request.queryString(queryParameterName, "true");
                }
            }
        }
        return request;
    }

    @RequiredArgsConstructor
    public static enum SSCAppVersionExclude {
        empty("onlyIfHasIssues"), no_assigned_issues("myAssignedIssues");

        @Getter
        private final String requestParameterName;
        
        @Override
        public String toString() {
            return super.toString().replace('_', '-');
        }
    }
}
