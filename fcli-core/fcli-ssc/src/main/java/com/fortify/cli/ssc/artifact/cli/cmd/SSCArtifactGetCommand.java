/*******************************************************************************
 * Copyright 2021, 2023 Open Text.
 *
 * The only warranties for products and services of Open Text 
 * and its affiliates and licensors ("Open Text") are as may 
 * be set forth in the express warranty statements accompanying 
 * such products and services. Nothing herein should be construed 
 * as constituting an additional warranty. Open Text shall not be 
 * liable for technical or editorial errors or omissions contained 
 * herein. The information contained herein is subject to change 
 * without notice.
 *******************************************************************************/
package com.fortify.cli.ssc.artifact.cli.cmd;

import com.fortify.cli.common.cli.util.EnvSuffix;
import com.fortify.cli.common.output.cli.cmd.IBaseRequestSupplier;
import com.fortify.cli.common.output.cli.mixin.OutputHelperMixins;
import com.fortify.cli.ssc._common.rest.SSCUrls;

import kong.unirest.HttpRequest;
import lombok.Getter;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;

@Command(name = OutputHelperMixins.Get.CMD_NAME)
public class SSCArtifactGetCommand extends AbstractSSCArtifactOutputCommand implements IBaseRequestSupplier {
    @Getter @Mixin private OutputHelperMixins.Get outputHelper; 
    @EnvSuffix("ARTIFACT") @Parameters(arity="1", descriptionKey="fcli.ssc.artifact.get.id")
    private String artifactId;
    
    @Override
    public HttpRequest<?> getBaseRequest() {
        return getUnirestInstance().get(SSCUrls.ARTIFACT(artifactId)).queryString("embed","scans");
    }
    
    @Override
    public boolean isSingular() {
        return true;
    }
}
