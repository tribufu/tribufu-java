#!/usr/bin/env pwsh

$env:JAVA_POST_PROCESS_FILE = ""

java -jar ./vendor/openapi-generator/openapi-generator-cli.jar generate `
    -i https://api.tribufu.com/openapi.json `
    -g java `
    -o . `
    --global-property apis,models,supportingFiles,apiDocs=false,modelDocs=false,apiTests=false,modelTests=false `
    --additional-properties=groupId=com.tribufu,library=okhttp-gson,invokerPackage=com.tribufu.generated,apiPackage=com.tribufu.generated.api,modelPackage=com.tribufu.generated.models,javaIndent=4`
    --openapi-normalizer SET_TAGS_FOR_ALL_OPERATIONS=TribufuGenerated `
    --skip-validate-spec
