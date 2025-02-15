<h2><center>Paintings Collectors Application<center></h2>
<p style="text-indent: 40px;"><i><b>Paintings Collectors</b> is an application designed for art enthusiasts and collectors to explore, acquire, and manage their collections of paintings. It offers a user-friendly platform where users can browse through various artworks, view details about each painting and add their art.</i><p/>
<p>There are several requirements you must follow in the implementation:</p>
<h3>1.	Database Requirements</h3>
The <b>Database</b> of the <b>Paintings Collectors</b> application needs to support <b>3 entities</b>:
<h4>User</h4>
<ul>
<li>    Has an <b>Id – "UUID-String" or Long</b></li>
<li>Has a <b>Username (unique, not null)</b>
    <ul>
        <li>Username length must be between 3 and 20 characters (inclusive of 3 and 20).</li>
    </ul>
</li>
<li>Has a <b>Password (not null)</b>
    <ul>
        <li>Password length must be between 3 and 20 characters (inclusive of 3 and 20).</li>
    </ul>
</li>
<li> Has an <b>Email (unique, not null)</b>
    <ul>
        <li>Must contain '@'.</li>
    </ul>
</li>
<li> Has an <b>Paintings</b> <i>(paintings added by user)</i>
    <ul>
        <li>The paintings contain users paintings. One <b>user</b> may have many <b>paintings</b> and one <b>painting</b> can be <b>created</b> by only one <b>user</b>.</li>
    </ul>
</li>
<li>Has a List &lt <b>FavouritePaining</b> &gt list of the favourite paintings</li>
</ul>
<h4>Painting</h4>
<ul>
<li> Has an <b>Id – "UUID-String" or Long</b></li> 
<li> Has a <b>Name (not null)</b>
     <ul>
        <li>Name length must be between 5 and 40 characters (inclusive of 5 and 40).</li>
    </ul>
</li>
<li> Has a <b>Author (not null)</b>
    <ul>
        <li>Author length must be between 5 and 30 characters (inclusive of 5 and 30)</li>
    </ul>
</li>
<li> Has a <b>Style (not null)</b> enumeration with values:
     <ul>
        <li>For <b>IMPRESSIONISM</b> – "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture."</li>
        <li>For <b>ABSTRACT</b> – "Abstract art does not attempt to represent recognizable subjects in a realistic manner."</li>
        <li>For <b>EXPRESSIONISM</b> – "Expressionism is a style of art that doesn't concern itself with realism."</li>
        <li>For <b>SURREALISM</b> – "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation."</li>
        <li>For <b>REALISM</b> – "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance."</li>
    </ul>	
</li> 
<li> Has an <b>Owner (not null)</b>
     <ul>
        <li>A painting <b>has one Owner</b>, the <b>User</b> who added it.</li>
    </ul>
</li> 
<li> Has an <b>Image URL (not null)</b>
     <ul>
        <li>Valid image URL containing <b>no more</b> than 150 characters.</li>
    </ul>
</li> 
<li> Has a <b>Votes (not null)</b>
    <ul>
        <li>The number of <b>votes</b> for the painting, if someone <b>voted</b>. The initial value is 0.</li>
    </ul>
</li> 
</ul>
<h4>FavouritePainting</h4>
<ul>
<li> Has an <b>Id – "UUID-String"</b></li> 
<li> Has an <b>Author of the paining(not null)</b>
    <ul>
        <li>Author length must be between 5 and 30 characters (inclusive of 5 and 30)</li>
    </ul>
</li> 
<li> Has a <b>Owner - who is owning this favourite paining</b></li> 
<li> Has an <b>Image URL (not null)</b>
    <ul>
        <li>Valid image URL containing <b>no more</b> than 150 characters.</li>
    </ul>
</li> 
<li> Has a <b>LocalDateTime</b> - when the user made this favourite</li>
</ul>
