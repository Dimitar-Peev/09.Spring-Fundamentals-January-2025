<h1>Workshop: Smart Wallet</h1>
<p><i>A <b>Smart Wallet Web App</b> is a digital platform that enables users to manage their finances, perform secure 
fund transfers, and a monitor transactions conveniently from their browser. You will have to create an application which 
has a several pages and core components.</i></p>

<h2>1.	Model</h2>
<p><i>This is the model layer of the application. There are some objects for you to implement first.</i></p>

<h3>User</h3>
<ul>
<li><b>id</b> – an <b>UUID.</b></li>
<li><b>username</b> – a <b>String,</b> the username of the user</li>
<li><b>firstName</b> – a <b>String,</b> the first name of the user</li>
<li><b>lastName</b> – a <b>String,</b> the last name of the user</li>
<li><b>profilePicture</b> – a <b>String,</b> URL containing link to picture of the user</li>
<li><b>email</b> – a <b>String,</b> email of the user</li>
<li><b>password</b> – a <b>String,</b> password of the user</li>
<li><b>role</b> – a <b>UserRole,</b> enumerated value (ADMIN, USER)</li>
<li><b>country</b> – a <b>Country,</b> enumerated value (BULGARIA, GERMANY, FRANCE)</li>
<li><b>isActive</b> – a <b>boolean</b> value which indicates whether the <b>User</b> is active</li>
<li><b>createdOn</b> – <b>LocalDateTime,</b> the date and time the <b>User</b> account was initialized</li>
<li><b>updatedOn</b> – <b>LocalDateTime,</b> the date and time the <b>User</b> account was updated</li>
<li><b>subscriptions</b> – a <b>List</b> of <b>Subscription</b> containing user's subscriptions</li>
<li><b>wallets</b> – a <b>List</b> of <b>Wallet</b> containing user's wallets</li>
</ul>

<h3>Wallet</h3>
<ul>
<li><b>id</b> – an <b>UUID</b></li>
<li><b>owner</b> – a <b>User,</b> the owner of the <b>Wallet</b></li>
<li><b>status</b> – a <b>WalletStatus,</b> enumerated value (ACTIVE, INACTIVE)</li>
<li><b>balance</b> – a <b>BigDecimal,</b> the amount available in the wallet</li>
<li><b>currency</b> – a <b>Currency</b> of the amount in the wallet</li>
<li><b>createdOn</b> – <b>LocalDateTime,</b> the date and time the new <b>Wallet</b> was created</li>
<li><b>updatedOn</b> – <b>LocalDateTime,</b> the date and time the <b>Wallet</b> state was updated</li>
</ul>

<h3>Subscription</h3>
<ul>
<li><b>id</b> – an <b>UUID</b></li>
<li><b>owner</b> – a <b>User</b>, the owner of the <b>Subscription</b></li>
<li><b>status</b> – a <b>SubscriptionStatus</b>, enumerated value (ACTIVE, COMPLETED, TERMINATED)</li>
<li><b>period</b> – a <b>SubscriptionPeriod</b>, enumerated value (MONTHLY, YEARLY)</li>
<li><b>type</b> – a <b>SubscriptionType</b>, enumerated value (DEFAULT, PREMIUM, ULTIMATE)</li>
<li><b>price</b> – a <b>BigDecimal</b>, the price of the subscription the user paid</li>
<li><b>renewalAllowed </b>– a <b>boolean</b> value which indicates whether the <b>Subscription</b> plan can be <b>automatically</b> renewed by our system - if the user buys a monthly subscription, then the system will automatically renew their subscription when the time comes, if the subscription is yearly, there will be no automatic renewal for the subscription</li>
<li><b>createdOn</b> – <b>LocalDateTime</b>, the date the <b>Subscription</b> was created</li>
<li><b>completedOn</b> – <b>LocalDateTime</b>, the date the <b>Subscription</b> was completed - <i>that could happen due to subscription change</i></li>
</ul>

<h3>Transaction</h3>
<ul>
<li><b>id</b> – an <b>UUID.</b></li>
<li><b>owner</b> – a <b>User,</b> the user for which the <b>Transaction</b> was initiated for</li>
<li><b>sender</b> – a <b>String,</b> identifier of the wallet <b>from</b> which we take the money</li>
<li><b>receiver</b> – a <b>String,</b> identifier of the wallet <b>to</b> which we give the money</li>
<li><b>amount</b> – a <b>BigDecimal,</b> the amount of the <b>Transaction</b></li>
<li><b>balanceLeft</b> – a <b>BigDecimal,</b> the remaining amount after the <b>Transaction</b></li>
<li><b>currency</b> – a <b>Currency</b> used for the <b>Transaction</b></li>
<li><b>type</b> – a <b>TransactionType,</b> enumerated value (DEPOSIT, WITHDRAWAL)</li>
<li><b>status</b> – a <b>TransactionStatus,</b> enumerated value (SUCCEEDED, FAILED)</li>
<li><b>description</b> – a <b>String,</b> description of the <b>Transaction</b></li>
<li><b>failureReason</b> – a <b>String,</b> the reason for the failed <b>Transaction</b> - <i>in case the transaction can’t be executed for some reason</i></li>
<li><b>createdOn</b> – <b>LocalDateTime,</b> the date the <b>Transaction</b> was made</li>
</ul>

<h2>2.	Data Access</h2>
<p>The application must support basic functionality involving <b>CRUD</b> (Create, Read, Update, Delete) operations.</p>
<p>You must implement repositories for every entity to persist data.</p>

<h2>3.	Business Logic</h2>
<h3>Registering a New User</h3>
<p>When a new user registers, they need:</p>
<ol>
<li><b>Account Creation:</b> Validate the username to ensure its unique and store the user's details securely. 
You must consider persisting user's sensitive data in a secure way!</li>
<li><b>Default Wallet Creation:</b> Automatically create a wallet for the user.</li>
<li><b>Default Subscription Setup:</b> Assign a free subscription to the user upon registration.</li>
</ol>

<h3>Wallet Creation</h3>
<p>Each user is assigned a wallet during registration. The wallet should be initialized with values defined in your configuration properties:</p>
<ul>
<li><b>Initial Balance: €20</b></li>
<li><b>Default Status: ACTIVE</b></li>
<li><b>Currency: Euro (EUR)</b></li>
<p>This ensures every user stats with a functional financial account tha is ready to use.</p>
</ul>

<h3>Subscription Creation</h3>
<p>Subscription provides access to app features. Every user starts with a <b>free default subscription,</b> configured as follows:  </p>
<ul>
<li><b>Type:</b> DEFAULT</li>
<li><b>Period:</b> From configuration (e.g., MONTHLY)</li>
<li><b>Price:</b> €0 (free by default)</li>
<li><b>Renewal Eligibility:</b> Renewals are allowed only for monthly subscriptions</li>
</ul>

<h3>Login</h3>
<p>The login functionality validates a user's credentials and ensures that only authorized users gain access</p>
<p><b>Steps to Consider:</b></p>
<ul>
<li>Verify the username exists</li>
<li>Confirm the password matches securely</li>
<li>Return the logged-in user</li>
</ul>

<h3>Top-Up: Adding Funds to Wallets</h3>
<p>Users can increase their wallet balance by performing a top-up operation.</p>
<p><b>Steps to Consider:</b></p>
<ul>
<li>Retrieve the wallet using its ID the user wants to top-up</li>
<li>Verify the wallet is <b>ACTIVE</b> before proceeding, <b>INACTIVE</b> wallets are not eligible for top-up</li>
<li>Add the top-up amount to the wallet balance</li>
<li>Record the transaction and save it for auditing purposes</li>
</ul>
