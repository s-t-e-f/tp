# User Guide

## Introduction

TraceYourProj is a desktop app for tracking resources for data science projects, optimized 
for use via a Command Line Interface (CLI).

## Quick Start

1. Download and install Java 11 or above in your device.
2. Get the latest version of TraceYourProj from [HERE](https://github.com/AY2021S2-CS2113-W10-3/tp/releases).
3. Double-click the .jar file to start the program. A command-line user interface (CLI) should appear quickly after execution.
4. Start using the app by entering commands to the user interface. Please refer to the Features section below for detailed description of each command.

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

### Finding resources: `find`

Finds resources in a specified project or in all projects based on a user-specified keyword

Format: `find k/KEYWORD [p/PROJECT_NAME]`

* The `KEYWORD` can consist of multiple words

Example of usage:

`find k/website p/CS2113`

`find k/dataset`


### Exiting TraceYourProj: `exit`

Exits the program.

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Find resources `find k/KEYWORD [p/PROJECT_NAME]`
* Exit TraceYourProj `exit`
