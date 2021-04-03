# TraceYourProj's User Guide

## Introduction

TraceYourProj is a desktop app for **tracking online resources** for **data science projects**, optimized 
for use via a Command Line Interface (CLI). 

## Quick Start

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
TraceYourProj - v0.1
Type 'help' for a list of command and related usage.
```

## Features 

### Notes about command format

* Words in **UPPER_CASE** are **parameters** specified by the **user**.  
  e.g.`list p/PROJECTNAME`, where `PROJECTNAME` is a parameter which can be used as `list p/CS2113 Documentation`.

* Items in **square brackets** are **optional**.  
  e.g. `delete p/PROJECT_NAME [i/INDEX]` can be used as `delete p/CZ2003` or `delete p/CZ2003 i/1`
  
* **Extra parameters** after commands that do not take in parameters such as `list-all` and `exit` will be **ignored**.  
  e.g.`exit 1234` will be interpreted as `exit`.  
  e.g.`list-all CZ2003` will be interpreted as `list-all`. 


---


### Viewing help: `help`
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
	Format: add p/PROJECT_NAME url/URL_LINK [d/LINK_DESCRIPTION]
delete: Deletes a resource from the resource list for a specified project.
	Format: delete p/PROJECT_NAME [i/INDEX]
edit: Edits a resource from the resource list for a specified project.
	Format: edit p/PROJECT_NAME i/INDEX [url/URL_LINK] [d/LINK_DESCRIPTION]
list: View the resource list for a specified project.
	Format: list PROJECT_NAME
list-all: Shows the resource list for all projects.
find: Find resources in a specified project or all projects related to a keyword.
	Format: find k/KEYWORD [p/PROJECT_NAME]
save: Saves the current projects and resources to a data file.
load: Loads the projects and resources from the data file if it exists.
exit: Exits the program.
------------------------------------------------------------------------
```

---


### Adding project(s) or resource(s) : `add`
Adds a resource to a Project.

Format : `add p/PROJECT_NAME url/URL [d/DESCRIPTION]`
* Adds a resource to a Project.
* If the given project name has already existed in the project list:
  * If the given URL exists in the resource list of the project:
    * Prompt the user that the resource with the same URL has already existed in the project.
  * If the given URL does **not** exist in the resource list of the project:
    * The resource will be appended to the resource project's resource list
* If the given project name does **not** exist in the project list:
  * A new project with the given project name will be created, and the resource will be appended to its resource list.
* Description is optional.

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
   The resource of the project "NUS" is overwritten.
   ```  
   
   It will **overwrite** the resource with the same URL which **already existed** in the project's resource list.  
   

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


---

### List the resources of all projects: `list-all`
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


### List the resources of one projects: `list PROJECTNAME`
Shows a list of all resources in one project created in TraceYourProj.

Format: `list PROJECTNAME`

**Example of usage:**

**Input:**
```
list NUS
```
**Output:**
```
--------------------------------------------------------
Project: NUS
Resource(s):
1): [2021-04-03] https://www.nus.edu.sg/ (Description: NUS Offical Website)
2): [2021-04-03] https://nusmods.com/
--------------------------------------------------------
```

---
### Deleting resource(s) : `delete`
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


### Editing a resource : `edit`
Edits an existing resource in the resource list based on the project.

Format: `edit p/PROJECT_NAME i/INDEX url/LINK [d/DESCRIPTION]`
* Edits the resource at the specified index of the specified project.
* The index refers to the index number shown in the displayed resource list of the specified project.
* This index must be a positive integer.

**Example of usage:**  

**Input:**  

```
edit p/Jester Jokes 2 url/https://www.kaggle.com/sameerdev7/joke-rating d/Jester Jokes Dataset'
```

**Output:**  

```

```

**Edits** the url and description of the 2nd resource in ‘Jester Jokes’ project to be https://www.kaggle.com/sameerdev7/joke-rating and Jester Jokes Dataset respectively.

---
### Finding resources: `find`

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


### Loading data from storage `load`

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


### Saving data to storage: `save`

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
### Exiting TraceYourProj: `exit`

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

## FAQ

**Q**: Can I add a resource to a project without providing description?

**A**: Yes, you can. It is because description is optional when adding a resource with `add` command.

---

## Command Summary

|Command|Syntax|Remark|
|--------|----------|--------|
|help|```help```|Shows the available commands of TraceYourProj.|
|add|`add p/PROJECT NAME url/URL [d/DESCRIPTION]`|Add a resource to a project|
|delete|`delete p/PROJECT_NAME [i/INDEX]`|Deletes the specified resource from the resource list based on the project.|
|edit|`edit p/PROJECT_NAME i/INDEX url/LINK [d/DESCRIPTION]`|Edits an existing resource in the resource list based on the project.|
|list|`list PROJECTNAME`|List a project's resources|
|list-all|`list-all`|List all projects and their respective resources|
|find|`find k/KEYWORD [p/PROJECT_NAME]`|Find resources|
|exit|`exit`|Exit TraceYourProj|
