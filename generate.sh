mvn io.quarkus:quarkus-maven-plugin:1.13.3.Final:create \
-DprojectGroupId=api.ferreteria \
-DprojectArtifactId=api-ferreteria \
-DclassName="api.ferreteria.service.MSEstadoFactura" \
-Dpath="/estado-factura" \
-Dextensions="resteasy,resteasy-mutiny,context-propagation,resteasy-jsonb,rest-client,jdbc-mysql,smallrye-openapi,hibernate-orm,io.quarkiverse.mybatis:quarkus-mybatis:0.0.7" \
-DnoExamples
