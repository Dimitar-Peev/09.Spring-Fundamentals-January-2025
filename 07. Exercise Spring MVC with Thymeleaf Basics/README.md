<h1>Workshop: Smart Wallet Part 2</h1>

<h2>1.	Register USER in DB</h2>
<p>Your task is to insert a user in the DB for testing purposes since we do not have State Management. 
Consider implementing a <b>User</b> initialization method.</p>

<h2>2. Index pages</h2>
<p>Get the index page, next we have to add Thymeleaf for the login and registration buttons. Define paths to:</p>

<h3>Index</h3>
<ul>
<li>Location: <b>/</b></li>
<li>HTML Template: <b>index.html</b></li>
</ul>

<h3>Login</h3>
<ul>
<li>Location: <b>/login</b></li>
<li>HTML Template: <b>login.html</b></li>
</ul>

<h3>Register</h3>
<ul>
<li>Location: <b>/register</b></li>
<li>HTML Template: <b>register.html</b></li>
</ul>

<h3>Home</h3>
<ul>
<li>Location: <b>/home</b></li>
<li>HTML Template: <b>home.html</b></li>
</ul>

<h2>3. Home page with user details</h2>
<p>Home page with user details. Populate the information with Thymeleaf.</p>

<h2>4. Home page buttons</h2>
<p>You have to make <b>GET</b> requests to the following endpoints:</p>

<h3>Edit Profile button</h3>
<p>This button has to send a <b>GET</b> request to:</p>
<ul>
<li>Location: <b>/users/{id}/profile</b></li>
<li>HTML Template: <b>profile-menu.html</b></li>
</ul>

<h3>Open Wallets button</h3>
<p>This button has to send a <b>GET</b> request to:</p>
<ul>
<li>Location: <b>/wallets</b></li>
<li>HTML Template: <b>wallets.html</b></li>
</ul>

<h3>Change button</h3>
<p>This button has to send a <b>GET</b> request to:</p>
<ul>
<li>Location: <b>/subscriptions</b></li>
<li>HTML Template: <b>upgrade.html</b></li>
</ul>

<h2>5. Subscriptions history Page</h2>
<p>To <b>GET</b> User subscriptions. Populate the information with Thymeleaf.</p>

<ul>
<li>Location: <b>/subscriptions/history</b></li>
<li>HTML Template: <b>subscriptions-history.html</b></li>
</ul>


<h2>8. Transactions Page</h2>
<p>To <b>GET</b> User transactions. Populate the information with Thymeleaf.</p>

<ul>
<li>Location: <b>/transactions</b></li>
<li>HTML Template: <b>transactions.html</b></li>
</ul>

