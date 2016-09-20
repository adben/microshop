## Microshop

This application is dedicated to development & local server only

  + git clone https://github.com/JBClion/microshop.git<br />
  + cd microshop<br />
  + mvn clean install<br />
  + cd microshop-ear<br />
  +  mvn appengine:devserver<br />
  
## Codelab

The current microservice project is not following best practices:

  + Datasets are local to instances and not relying on memcache or datastore.
  + Instances are statefull

Update the the current version of this application in order to use memcache and datastore. Make sure your services are stateless.
  
For any example, you can rely on the following repo: https://github.com/JBClion/todolist

Best Luck!
