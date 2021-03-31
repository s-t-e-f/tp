# User Guide

## Introduction

TraceYourProj is a desktop app for tracking resources for data science projects, optimized 
for use via a Command Line Interface (CLI).

## Quick Start

1. Download and install Java 11 or above in your device.
2. Get the latest version of TraceYourProj from [HERE](https://github.com/AY2021S2-CS2113-W10-3/tp/releases).
3. Execute the .jar file via terminal. A command-line user interface (CLI) should appear quickly after execution.
   * For Windows user, you may input "java -jar (location of the .jar file)" through command prompt
4. Start using the app by entering commands to the user interface. Please refer to the Features section below for detailed descriptions of each command.

## Features 

### Notes about command format
* Words in UPPER_CASE are parameters specified by the user.
  * e.g. in delete i/INDEX, INDEX is a parameter which can be used as delete 3. This will delete the third resource in the list.
* Items in square brackets are optional.
  * e.g. Command for editing resources:
p/PROJECT_NAME i/INDEX [url/LINK] [d/DESCRIPTION] can be used as p/Jester’s jokes i/2 url/https://www.kaggle.com/sameerdev7/joke-rating or as p/Jester’s jokes i/2
* Items with … after them can be used multiple times with the exception of zero times.
  * e.g. list p/PROJECT_NAME… can be used as: list p/Jester’s jokes p/Titanic.
* Extra parameters after commands that do not take in parameters such as list and exit will be ignored.
  * e.g. if the command entered is exit 1234, it will be interpreted as exit.
---
### Adding project(s) or resource(s) : `add`
Adds a resource to a Project.

Format : `add p/PROJECT_NAME url/URL [d/DESCRIPTION]`
* Adds a resource to a Project.
* If the given project name has already existed in the project list:
  * If the given URL exists in the resource list of the project:
    * The original resource will be overwritten.
  * If the given URL does **not** exist in the resource list of the project:
    * The resource will be appended to the resource list.
* If the given project name does **not** exist in the project list:
  * A new project with the given project name will be created, and the resource will be appended to its resource list.
* Description is optional.

Examples :
1. Assume project "NUS" does not exist in the project list:
<br>`add p/NUS url/https://www.nus.edu.sg/ d/NUS website`
   creates a project named "NUS" and the resource (URL and description) will be added into its resource list.
2. Assume a project "NUS" exists in the project list, and a resource with URL = https://www.nus.edu.sg/ exists:
<br> `add p/NUS url/https://www.nus.edu.sg/ d/NUS Offical Website`
   overwrites the original resource.
2. Assume a project "NUS" exists in the project list, and a resource with URL = https://nusmods.com/ does not exist:
   <br> `add p/NUS url/https://nusmods.com/`
   appends the resource to the resource list of the project "NUS".
   
---
### Deleting resource(s) : `delete`
Deletes the specified resource from the resource list based on the project.

Format : `delete p/PROJECT_NAME [i/INDEX]`
* Deletes the resource at the specified index of the specified project.
* The index refers to the index number shown in the displayed resource list of the specified project.
* This index must be a positive integer.
* If the index is not given, all the resources for that specified project are deleted.

Examples : 
<br>`delete p/Jester Jokes i/3` deletes the 3rd resource in the resource list of the project 'Jester Jokes'. 
<br> `delete p/Jester Jokes` deletes all resources from the project 'Jester Jokes'.

### Editing a resource : `edit`
Edits an existing resource in the resource list based on the project.

Format: `edit p/PROJECT_NAME i/INDEX url/LINK [d/DESCRIPTION]`
* Edits the resource at the specified index of the specified project.
* The index refers to the index number shown in the displayed resource list of the specified project.
* This index must be a positive integer.

Examples : 
<br> `edit p/Jester Jokes 2 url/https://www.kaggle.com/sameerdev7/joke-rating d/Jester Jokes Dataset'`
<br>Edits the url and description of the 2nd resource in ‘Jester Jokes’ project to be https://www.kaggle.com/sameerdev7/joke-rating and Jester Jokes Dataset respectively.

### List the resources of all projects: `list-all`
Shows a list of all resources in all projects created in TraceYourProj.  

Format: `list-all`

Example of usage:  
`list-all`

### List the resources of one projects: `list PROJECTNAME`
Shows a list of all resources in one project created in TraceYourProj.

Format: `list PROJECTNAME`

Example of usage:  

`list CS2113`  
`list CS3245 Project`  
`list IT3011 Machine Learning References`

### Finding resources: `find`

Finds resources in a specified project or in all projects based on a user-specified keyword

Format: `find k/KEYWORD [p/PROJECT_NAME]`

* The `KEYWORD` can consist of multiple words

Example of usage:

`find k/website p/CS2113`

`find k/dataset`

### Loading data from storage `load`

Loads the projects and resources from the data storage text file.

Format: `load`

### Saving data to storage: `save`

Saves the project and resources to the data storage text file.

Format: `save`

### Exiting TraceYourProj: `exit`

Exits the program.

Format: `exit`

## FAQ

**Q**: Can I add a resource to a project without providing description?

**A**: Yes, you can. It is because description is optional when adding a resource with `add` command.

## Command Summary

{Give a 'cheat sheet' of commands here}

* List all projects and their respective resources `list-all`
* List a project's resources `list PROJECTNAME`
* Find resources `find k/KEYWORD [p/PROJECT_NAME]`
* Exit TraceYourProj `exit`
