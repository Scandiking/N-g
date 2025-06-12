# Næg

![Naeg logo](./gfx/Nag-logo.ico)

***Never Forget Again — We'll Make Sure Of It...***

<!-- Table of Contents -->

<details>

<summary>
Table of Contents
</summary>

<!-- TOC -->

* [Næg](#næg)
    * [<a id="what-is-næg"></a>What is Næg?](#a-idwhat-is-nægawhat-is-næg)
    * [<a id="how-to-add-tasks-to-people-in-a-room"></a>How to add tasks to people in a room](#a-idhow-to-add-tasks-to-people-in-a-roomahow-to-add-tasks-to-people-in-a-room)
    * [How to install](#how-to-install)
    * [Næg frequency](#næg-frequency)
        * [Low](#low)
        * [Medium](#medium)
        * [Næg special](#næg-special)
        * [Custom](#custom)

<!-- TOC -->
</details>

### What is Næg?

First and foremost, this is a university project and a part of an exam. We cannot guarantee for security.

Second, Næg is a React app that helps you get others to do what they should do.   
How do you do that? You add tasks that you assign to persons in a room. What sets Næg apart from other task managing
apps is the ability to set a `Næg frequency` for each task. This means you can nag people with push notifications to do
their tasks with varying intensity, from a gentle reminder to an all-out bombardment of notifications.

### How to install

<details>
  <summary>CLI</summary>
  <h4>Instructions for CLI installation.</h4>
<ol>
  <li>Open a command window</li>
  <li>Enter <code>github clone https://github.com/Scandiking/N-g</code></li>
  <li>Press enter to continue</li>
  <li>Unzip the folder</li>
  <li>Go in <code>/backend/src/main/java/com/nag</code> and enter <code>javac NagApplication</code></li>
  <li>Click enter</li>
  <li>Enter <code>java NagApplication</code> to run the backend</li>
  <li>Click enter</li>
  <li>Change directory from the already open terminal to <code>/frontend</code> in the newly unzipped folder by entering <code>cd..</code> and enter, then <code>cd frontend</code>.</li>
  <li>Enter <code>npm start</code> to start the frontend (check localhost:3000 or whatever the console output says if it does not open automatically)</li>
</ol>

</details>

<details>
  <summary>autorun.bat</summary>
  <h4>Instructions for installing the application using the autorun.bat-file.</h4>
<ol>
<li>Download the zip from the sidebar to the right (on the <a href="https://github.com/Scandiking/N-g">GitHub repo</a>)</li>
<li>Unzip the folder</li>
<li>Click the <code>StartNag.bat</code> file</li>
<li>The browser will open Næg in a new tab if everything goes according to the plan, and the console will say what is wrong if it does not go according to plan.</li>
</ol>
</details>

### How to add tasks to people in a room

Begin by adding a

- __Room__  
  The space is the arena you want tasks done in. You want one space for `Home` and one for `School`? You add two `Room`
  s. This is so you don't næg your colleagues to take out the trash at your place.


- __People__  
  The people are the persons you want to do the `Task`s you add. While making tasks you assign those tasks to specific
  people. You invite people to your `Room` by using the `Sidebar` and choose whether to send them a mail with a link, or
  an SMS with a link.


- __Tasks__  
  The tasks are done by the People added to a Room. You add a `task` by clicking the `+` button in the lower right
  corner or by using the `Sidebar`. This is also where you will set the [næg frequency](#næg-frequency).

### Næg frequency

#### Low

- This notifies the assigned persons to do the task with typical intensity, much like a notification on Snapchat and
  Messenger.

#### Medium

- This notifies the assigned persons to do the task with medium intensity. Repeated notifications.

#### Næg special

- This is what sets Næg apart from other productivity apps. The assigned persons will get a personalized exponentially
  increasing notification regime. Use sparingly for most people, but feel free to torment lazy roommates not adhering to
  the rental contract.

#### Custom

- You can also customize the frequency of notifications, or choose not to notify users at all within certain timeframes.
  Great for those who work night shifts or to adjust for personal differing schedules.

---

<p style="text-align:center; font-style:italic;">«Jeg er ikke bekymret for Gruppe 7»</p>

<div style="text-align:center;">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Flag_of_Norway.png/330px-Flag_of_Norway.png" alt="Flag of Norway" style="width:50%;">
</div>  

<p style="text-align:center;">Made with ❤️ by the students of <a href="https://www.usn.no/english/">Universitetet i Sørøst-Norge</a></p> 








