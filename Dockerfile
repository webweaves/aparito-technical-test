FROM jboss/wildfly
ADD deployments/version_1_0_0/primenumbers-rest-api.war /opt/jboss/wildfly/standalone/deployments/
ADD deployments/version_1_0_0/PrimeNumbers.war /opt/jboss/wildfly/standalone/deployments/

