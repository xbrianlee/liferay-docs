---
header-id: kaleo-forms
---

# Kaleo Forms

[TOC levels=1-4]

Business processes are often form-based and workflow-driven. They start with 
entered data and progress by sending that data to other people or groups. Then
it's processed in some way (for example, further data is entered or approval is
granted), and the process moves on until completion, when each interested party
has seen and manipulated the data. To write an app for each of these processes
is laborious. It's much better to have a tool for quickly defining a process to
suit each use case. The process architect must define both the data that gets
collected and the process the data moves through to reach its final state. To
accomplish this, @product@ already includes the
[Dynamic Data Lists app](/docs/7-2/user/-/knowledge_base/u/creating-data-definitions)
for defining forms, and the 
[Workflow Designer app](/docs/7-2/user/-/knowledge_base/u/workflow-designer) 
for designing workflows. The Kaleo Forms solution combines the features of these
apps, letting you use a single UI to design an integrated process for sending 
forms through a workflow. 

## Creating Kaleo Forms Process

To start creating a Kaleo Forms Process you need to get to Kaleo Forms Admin: 

1.  Go to *Site Administration* (your Site's menu) &rarr; *Content & Data* &rarr; 
    *Kaleo Forms Admin*. The Kaleo Forms app appears with a list of any defined 
    processes. 

2.  Click the *Add* button 
    (![Add](../../images/icon-add.png)) 
    to open the New Process Wizard. 

3.  Give the process a name and a description and click *Next*. 

    ![Figure 1: Add a Kaleo Forms Process to link a form with a workflow definition.](../../images-dxp/kaleo-forms-add.png)

4.  Define the fields that can appear in your process's forms. There are two 
    ways to do this: 

    -   Use an existing field set. Click the field set's Actions 
        button 
        (![Actions](../../images/icon-actions.png)) 
        and select *Choose*.

    -   Create a new field set/data definition. Click the 
        *Add Field Set* button. If you need help with this, see the 
        documentation on
        [creating data definitions](/docs/7-2/user/-/knowledge_base/u/creating-data-definitions). 
        After creating the field set, select it as you would an existing field 
        set. 

    Click *Next* to move to the wizard's next step. 

    ![Figure 2: Define and choose your form's fields.](../../images-dxp/kaleo-forms-fields.png)

5.  Select a workflow to use for your forms. To do this, click the workflow's 
    *Actions* button 
    (![Actions](../../images/icon-actions.png)) 
    and select *Choose*. 

    You can also edit an existing workflow or create a new one: 

    -   To edit a workflow, click its *Actions* button 
        (![Actions](../../images/icon-actions.png)) 
        and select *Edit*. 

    -   To begin creating a new workflow, click the *Add Workflow* button. 

    In either case, you use the same UI to edit/create the workflow. This UI is 
    called 
    [Workflow Designer](/docs/7-2/user/-/knowledge_base/u/workflow-designer). It 
    lets you create your workflow graphically instead of via code. 

    Once you select a workflow to use with your forms, click *Next*. 

    ![Figure 3: This example workflow has three tasks that happen sequentially.](../../images-dxp/kaleo-forms-spa-order-definition.png)

6.  Select or create a form to use for each workflow task. To do this, click 
    each task's *Actions* button 
    (![Actions](../../images/icon-actions.png)) 
    and select *Assign Form*. On the screen that appears, select an existing 
    form or click the *Add* button 
    (![Add](../../images/icon-add.png)) 
    and to create one. 

    Click *Save* when you're finished. Your process is done and appears in Kaleo 
    Forms Admin's table. 

    ![Figure 4: Assign a form to each task in the workflow, and for the initial state.](../../images-dxp/kaleo-forms-task-forms.png)

## Adding Records to a Process

To add records to a process, click it in Kaleo Forms Admin and then click the 
*Add* button 
(![Add](../../images/icon-add.png)). 
This brings up the form you assigned to the workflow's initial state. Fill it 
out and click *Save*. Once submitting the initial form, the workflow engine then 
takes over and moves through each task in the workflow. Whatever Users or Roles 
you assigned to the tasks receive notifications, and the task appears in the 
*Assigned to My Roles* section of the My Workflow Tasks app. A notification also 
appears in the Notifications app. Once in the task, the user views and approves 
the form or clicks the *Edit* button. At this point, the workflow task forms you 
created come into play. Each assigned user fills out the form, saves it, and 
sends it along in the workflow. 
