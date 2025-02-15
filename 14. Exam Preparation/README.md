<h2><center>Philately Application<center></h2>
<p style="text-indent: 40px;"><i><b>Philately</b> is the collection and study of postage stamps and related postal items. It is a hobby enjoyed by people of all ages worldwide, and it can be both a leisure activity and an academic pursuit. Philately began shortly after introducing the first postage stamp, the Penny Black, in 1840 in Great Britain. As postal systems expanded and evolved, so did the hobby of collecting stamps.</i><p/>
<p>There are several requirements you must follow in the implementation:</p>
<h3>1.	Database Requirements</h3>
The Database of the Philately application needs to support 3 entities:
<h4>User</h4>
<ul>
<li>    Has an <b>Id – UUID</b></li>
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
<li>Has a List &lt <b>Stamp</b> &gt list of the <b>added</b> stamps</li>
<li>Has a List &lt <b>WishedStamp</b> &gt list of the wished stamps</li>
</ul>
<h4>Stamp</h4>
<ul>
<li> Has an <b>Id – UUID</b></li> 
<li> Has a <b>Name (not null)</b>
     <ul>
        <li>Name length must be between 5 and 20 characters (inclusive of 5 and 20).</li>
    </ul>
</li>
<li> Has a <b>Description (not null)</b> (a brief description of what the stamp depicts)
    <ul>
        <li>Description length must be between 5 and 25 characters (inclusive of 5 and 25)</li>
    </ul>
</li>
<li> Has a <b>Paper (not null)</b> (the type of paper on which the stamp is printed) enumeration with values:
     <ul>
        <li>For <b>WOVE_PAPER</b> – "Has an even texture without any particular distinguishing features."</li>
        <li>For <b>LAID_PAPER</b> – "When held up to the light, shows parallel lines of greater or less width running across the stamp."</li>
        <li>For <b>GRANITE_PAPER</b> – "Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye."</li>
    </ul>	
</li> 
<li> Has an <b>Image URL (not null)</b>
     <ul>
        <li>Valid image URL containing <b>no more</b> than 150 characters.</li>
    </ul>
</li> 
<li> Has an <b>Owner</b>
    <ul>
        <li>A stamp <b>has one Owner</b>, the <b>User</b> who added it.</li>
    </ul>
</li> 
</ul>
<h4>WishedStamp</h4>
<ul>
<li> Has an <b>Id – UUID</b></li> 
<li> Has a <b>Name (not null)</b>
    <ul>
        <li>Name length must be between 5 and 20 characters (inclusive of 5 and 20).</li>
    </ul>
</li> 
<li> Has a <b>Description (not null)</b> (a brief description of what the stamp depicts)
     <ul>
        <li>Description length must be between 5 and 25 characters (inclusive of 5 and 25)</li>
    </ul>
</li> 
<li> Has an <b>Image URL (not null)</b>
    <ul>
        <li>Valid image URL containing <b>no more</b> than 150 characters.</li>
    </ul>
</li> 
<li> Has an <b>Owner – who is owning this wished stamp.</b></li>
</ul>
