FROM jboss/wildfly
ADD primenumbers-rest-api/target/primenumbers-rest-api.war /opt/jboss/wildfly/standalone/deployments/
ADD web-frontend/target/PrimeNumbers.war /opt/jboss/wildfly/standalone/deployments/

