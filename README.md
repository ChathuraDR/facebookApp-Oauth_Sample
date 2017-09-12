# facebookApp-Oauth_Sample
Retrieving Facebook profile resources using OAuth on behalf of the user

This is a Java maven web Application Sample.so,
***Download tomcat,maven and set path variables***
First you have to Install & Configure Apache tomcat & Apache Maven on your computer.for an example if you're a **windows** user,
    
   Download Apache tomcat from [here.](https://tomcat.apache.org/download-70.cgi)Download the **zip** file.(If you're already using xamp, it has apache tomcat.so you don't have to install tomcat again.)
   
   Download Apache maven from [here.](https://maven.apache.org/download.cgi)Download the **bin.zip** file.
   
   create a folder(e.g. C:\Apache).unzip these two into that direcotory.now you have these two in Apache folder.Now you have to set environment variables in order to run them from cmd promt.(hit Win key+F,search type would be settings and then type "system settings" and then select "View advanced system settings" from the search results.then goto Advanced->Environment Variables).To do that click on new button under system variables, 
   
   1. Set Variable name : CATALINA_HOME & Variable value : <path to your tomcat directory>(e.g. C:\Apache\tomcat7).then click ok.
   2. Click again on New button to add maven.Set Variable name : MAVEN_HOME & Variable value : <path to your maven directory>(e.g. C:\Apache\maven).then click ok.
   3. Now you have to edit path variable,look for a variable call "path" under system variable.Select the variable and click "edit" button.append these line at the end of the "Variable value", ;CATALINA_HOME\bin;MAVEN_HOME\bin.
   
   Open the command prompt and run these command to check whether variables are correctly set or not.
   
   1. run `catalina` to check tomcat.
   2. run `mvn -v` to check maven.if you get positive results you're good to go.if not recheck it.

***Download and extract my repository in somewhere in your pc***
Edit client id,app id according to your details.If you're new to facebook app developing, refer my blogpost about [Retrieving User resources from facebook using OAuth](http://magcyber.blogspot.com/2017/05/retrieving-user-resources-from-facebook.html).

After you edited those details, open the command prompt, cd into the facebookFunApp directory and run these commands.

   1. `catalina start` to start tomcat server.
   2. `mvn clean install` to build up the maven project.after run this command you'll see a directory call "target" inside facebookFunApp directory.copy the facebookFunApp.war file into the "webapps" directory inside the tomcat directory.(or goto http://localhost:8080/ and select Manager App,Enter user name password that you configured on tomcat-users.xml file.click "Choose file" under "war file deploy" and select your war file inside facebookFunApp/target, and hit deploy.)
    
   Then you'll be able to access the webbapp from the browser.enter "localhost:8080/facebookFunApp/" and hit enter.
    
    
