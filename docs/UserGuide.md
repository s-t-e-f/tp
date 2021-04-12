# TraceYourProj's User Guide

## Table of Contents
#### [1. Introduction](#intro)
#### [2. Quick Start](#start)
#### [3. Features](#features)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Notes about command](#notes)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Viewing help](#help)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Adding project(s) or resource(s)](#add)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[List the resources of all projects](#listall)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[List the resources of one project](#list)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Deleting resource(s)](#delete)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Editing a resource](#edit)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Finding resources](#find)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Loading data from storage](#load)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Saving data to storage](#save)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Exiting TraceYourProj](#exit)
#### [4. FAQ](#faq)
#### [5. Command Summary](#commandSummary)

---

## <a id="intro">Introduction</a>

TraceYourProj is a desktop app for **tracking online resources** for **data science projects**, optimized 
for use via a Command Line Interface (CLI). 

## <a id="start">Quick Start</a>

1. Download and install **Java 11** or above in your device.
2. Get the **latest version** of TraceYourProj from [HERE](https://github.com/AY2021S2-CS2113-W10-3/tp/releases).
3. Execute the .jar file via terminal. A command-line user interface (CLI) should appear quickly after execution.
   * For **Windows** user, you may input "java -jar (location of the .jar file)" through command prompt
4. Start using the app by **entering commands** to the user interface. Please refer to the **Features** section below 
   for **detailed descriptions** of **each command**.
   
Upon running the jar file, you should see this on the command prompt console:
```
 _____                                                  ___              _
/__   \ _ __  __ _   ___  ___ /\_/\ ___   _   _  _ __  / _ \ _ __  ___  (_)
  / /\/| '__|/ _` | / __|/ _ \\_ _// _ \ | | | || '__|/ /_)/| '__|/ _ \ | |
 / /   | |  | (_| || (__|  __/ / \| (_) || |_| || |  / ___/ | |  | (_) || |
 \/    |_|   \__,_| \___|\___| \_/ \___/  \__,_||_|  \/     |_|   \___/_/ |
                                                                      |__/

Team Project of CS2113-W10-3.
TraceYourProj
Type 'help' for a list of command and related usage.
```

<div style="page-break-after: always;"></div>

## <a id="features">Features</a>

### <a id="notes">Notes about command</a>

* Words in **UPPER_CASE** are **parameters** specified by the **user**.  
  e.g.`list p/PROJECTNAME`, where `PROJECTNAME` is a parameter which can be used as `list p/CS2113 Documentation`.

* Items in **square brackets** are **optional**.  
  e.g. `delete p/PROJECT_NAME [i/INDEX]` can be used as `delete p/CZ2003` or `delete p/CZ2003 i/1`
  
* **Extra parameters** after commands that do not take in parameters such as `list-all` and `exit` will be **ignored**.  
  e.g.`exit 1234` will be interpreted as `exit`.  
  e.g.`list-all CZ2003` will be interpreted as `list-all`.
  
* All leading and trailing space of user input will be removed.

* Command parameters have to be provided in correct formats and orders, syntax of each command is provided in Feature 
  session. 


---

### <a id="help">Viewing help: `help`</a>

Shows the available commands of TraceYourProj.

Format: `help`

**Examples of usage:**  
Input:
```
help
```

Output:
```
------------------------------------------------------------------------
Here are the available commands:
add: Adds a resource to a project.
	Format: add p/PROJECT_NAME url/URL_LINK [d/LINK_DESCRIPTION c/true]
delete: Deletes a resource from the resource list for a specified project.
	Format: delete p/PROJECT_NAME [i/INDEX]
edit: Edits a resource from the resource list for a specified project.
	Format: edit p/PROJECT_NAME i/INDEX [url/URL_LINK] [d/LINK_DESCRIPTION]
list: View the resource list for a specified project.
	Format: list p/PROJECT_NAME
list-all: Shows the resource list for all projects.
find: Find resources in a specified project or all projects related to a keyword.
	Format: find k/KEYWORD [p/PROJECT_NAME]
save: Saves the current projects and resources to a data file.
load: Loads the projects and resources from the data file if it exists.
exit: Exits the program.
------------------------------------------------------------------------
```

---

### <a id="add">Adding project(s) or resource(s) : `add`</a>
Adds a resource to a Project.

Format : `add p/PROJECT_NAME url/URL [d/DESCRIPTION c/CHECK]`
* Adds a resource to a Project.
* If the given project name has already existed in the project list:
  * If the given URL exists in the resource list of the project:
    * Prompt the user that the resource with the same URL has already existed in the project.
  * If the given URL does **not** exist in the resource list of the project:
    * The resource will be appended to the resource project's resource list
* If the given project name does **not** exist in the project list:
  * A new project with the given project name will be created, and the resource will be appended to its resource list.
* d/DESCRIPTION and c/true are optional.
* If c/true is provided, a URL check will be performed (Requires online access).
    *The program only accepts c/ture, c/{other words} (e.g. c/Happy) would be omitted.

**Example of usage:**
1. Project "NUS" **does not exist** in the list:  
   
    **Input:**  
   ```
   add p/NUS url/https://www.nus.edu.sg/ d/NUS website
   ```
   **Output:**  
   ```
   The resource is added into the new project "NUS".
   ```  
   
   A project named **"NUS"** is **created** and the **resource** (URL and description) is **added** into its **resource list**.  
     

2. Project "NUS" and its resource's URL - https://www.nus.edu.sg/ **already exists** in the project list:
    **Input:**  
   ```
   add p/NUS url/https://www.nus.edu.sg/ d/NUS Offical Website
   ```
   **Output:** 
   ```
   A resource with The same URL has already existed in its resource list. If you want to edit the resource, please use "edit" command.
   ```  
   
   The user is prompted that the resource with the same URL has already existed in the project. 
   

3. Assume a project "NUS" **exists** in the project list, and a resource with URL = https://nusmods.com/ **does not exist**:  
   **Input:**  
   ```
   add p/NUS url/https://nusmods.com/
   ```
   
    **Output:**
   ```
    The resource is added to the existing project "NUS".
   ```  
   
   It will **append** the resource to the resource list of the project "NUS".  

4. Assume the user's device could access internet:  
   **Input:**
   ```
   add p/NUS url/nusmods c/true
   ```

   **Output:**
   ```
    Error: Resource failed to be added. (Reason: URL provided is not a valid URL.)
   ```  

   The user is prompted that the provided URL is invalid since it could not pass the URL test.


---
### <a id="listall">List the resources of all projects: `list-all`</a>
Shows a list of all resources in all projects created in TraceYourProj.

Format: `list-all`

**Example of usage:**

**Input**:
```
list-all
```
**Outputs**:  
1. If there are no projects:
    ```
    Here is the list of all project(s) and it's resource(s)!
    --------------------------------------------------------
    ```

2. If there are multiple projects and its resources:
    ```
    Here is the list of all project(s) and it's resource(s)!
    --------------------------------------------------------
    Project 1: NUS
    Resource(s):
    1): [2021-04-03] https://www.nus.edu.sg/ (Description: NUS Offical Website)
    2): [2021-04-03] https://nusmods.com/
    --------------------------------------------------------
    Project 2: NTU
    Resource(s):
    1): [2021-04-03] https://ntu.com/
    --------------------------------------------------------
    ```


---

### <a id="list">List the resources of one project: `list p/PROJECTNAME`</a>
Shows a list of all resources in **one** project created in TraceYourProj.

Format: `list p/PROJECTNAME`

**Example of usage:**

**Input:**
```
list p/NUS
```

<div style="page-break-after: always;"></div>

**Output:**
```
--------------------------------------------------------
Project: NUS
Resource(s):
1): [2021-04-03] https://www.nus.edu.sg/ (Description: NUS Offical Website)
2): [2021-04-03] https://nusmods.com/
--------------------------------------------------------
```

For `list p/PROJECT_NAME`, only one project name can be used at one time. This feature doesn't support multiple project names. 
It doesn't support two parameters like `list p/CZ2003 p/IT3011`. Supporting more than 1 project name for this feature
will be for our future implementations.


---
### <a id="delete">Deleting resource(s) : `delete`</a>
Deletes the specified resource from the resource list based on the project.

Format : `delete p/PROJECT_NAME [i/INDEX]`
* Deletes the resource at the **specified index** of the **specified project**.
* `INDEX` refers to the index number shown in the displayed resource list of the specified project.
* `INDEX` must be a **positive integer** and **less than** the **total number of resources** in that **specified project**.
* If the `INDEX` is **not given**, all the resources for that specified project are deleted.

**Example of usage:**  

1. Deletes the 3rd resource in the resource list of the project 'NUS'.  
   <br>
   **Input:**  
   
   ```
   delete p/NUS i/3
   ```
   
   **Output:**
   ```
   The resource is deleted from the project "NUS".
   ```
2. Deletes all resources from the project 'NUS'.
   <br>  
   **Input:**  
   ```
   delete p/NUS
   ```
   **Output:**
   ```
   All the resources in NUS has been deleted.
   ```
   

---

### <a id="edit">Editing a resource : `edit`</a>
Edits an existing resource in the resource list based on the project.

Format: `edit p/PROJECT_NAME i/INDEX url/LINK [d/DESCRIPTION]`
* Edits the resource at the specified index of the specified project.
* The index refers to the index number shown in the displayed resource list of the specified project.
* This index must be a positive integer.
* Note: `d/DESCRIPTION` is an optional parameter. If not specified, only the url will be edited.

**Example of usage:**  

**Input:**  

```
edit p/Jester's Jokes i/2 url/https://www.kaggle.com/sameerdev7/joke-rating d/Jester Jokes Dataset
```

**Output:**  

```
The resource is successfully edited to : 
    [2021-04-03] https://www.kaggle.com/sameerdev7/joke-rating (Description: Jester Jokes Dataset)
```

Edits the url and description of the 2nd resource in ‘Jester Jokes’ project to be https://www.kaggle.com/sameerdev7/joke-rating and Jester Jokes Dataset respectively.

---
### <a id="find">Finding resources: `find`</a>

Finds resources in a specified project or in all projects based on a user-specified keyword

Format: `find k/KEYWORD [p/PROJECT_NAME]`

* The `KEYWORD` can consist of multiple words

Example of usage:
1. Find the resources using a keyword, 'website' under project "CS2113".  
   **Input:** 
   ```
    find k/website p/NUS
   ```
   **Output:**
   ```
    --------------------------------------------------------
    Project: NUS
    1): [2021-04-03] https://www.nus.edu.sg/ (Description: NUS website)
    --------------------------------------------------------
   ```

2. Find the resources using a keyword, 'website' in all projects.  
    **Input:**
   ```
   find k/website
   ```
   **Output:**  
   ```
    Here is the list of all project(s) and its resource(s) matching the keyword!
    --------------------------------------------------------
    Project 1: NUS
    1): [2021-04-03] https://www.nus.edu.sg/ (Description: NUS website)
    --------------------------------------------------------
    Project 2: CS2113
    1): [2021-04-03] https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html (Description: CS2113 website)
    --------------------------------------------------------
   ```
   
---

### <a id="load">Loading data from storage `load`</a>

Loads the projects and resources from the data storage text file.  

Format: `load`

**Example of usage:**  

**Input:**
```
load
```

**Output:**
```
Loaded projects from storage
```

---

### <a id="save">Saving data to storage: `save`</a>

Saves the project and resources to the data storage text file.

Format: `save`

**Input:**
```
save
```

**Output:**
```
Saved projects to storage
```

---
### <a id="exit">Exiting TraceYourProj: `exit`</a>

Exits the program.

Format: `exit`

**Input:**
```
exit
```

**Output:**
```
Thank you for using TraceYourProj!
Hope you have a wonderful day.


```

---

## <a id="faq">FAQ</a>

**Q**: Do I need to create a new project before adding resource to it?

>No, you do not need to do so. With `add` command, if the provided project name is not found in the project list, 
a new project will be created, and the provided resources will be added to the newly created project automatically.

**Q**: Can I add a resource to a project without providing description?

>Yes, you can. It is because description is optional when adding a resource with `add` command.

---
<div style="page-break-after: always;"></div>

## <a id="commandSummary">Command Summary</a>


|Command|Syntax|Remark|
|--------|----------|--------|
|help|```help```|Shows the available commands of TraceYourProj.|
|add|`add p/PROJECT NAME url/URL [d/DESCRIPTION c/true]`|Add a resource to a project|
|delete|`delete p/PROJECT_NAME [i/INDEX]`|Deletes the specified resource from the resource list based on the project.|
|edit|`edit p/PROJECT_NAME i/INDEX url/LINK [d/DESCRIPTION]`|Edits an existing resource in the resource list based on the project.|
|list|`list p/PROJECT_NAME`|List a project's resources|
|list-all|`list-all`|List all projects and their respective resources|
|find|`find k/KEYWORD [p/PROJECT_NAME]`|Find resources|
|exit|`exit`|Exit TraceYourProj|
