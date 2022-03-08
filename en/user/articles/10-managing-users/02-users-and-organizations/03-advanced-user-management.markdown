---
header-id: user-management-additional-topics
---

# User Management: Additional Topics

[TOC levels=1-4]

You've learned the basics on adding and editing Users, but there are additional
important topics that go beyond the most basic tasks an administrator must
perform. Read on to learn about these. 

## Password Resets

The Add User functionality includes a *Require Password Reset* checkbox at the
bottom of the Password form. The default password policy does not even allow
administrators to deselect this option. As the administrator, however, you can
modify the default password policy so that this box becomes usable. 

1.  Navigate to *Password Policies* in Control Panel &rarr; Users.

2.  Click on the *Default Password Policy*.

3.  Deselect the *Change Required* switcher in the Password Changes section. Now
    you can decide whether users you add must reset their passwords. 

See [Password Policies](/docs/7-2/user/-/knowledge_base/u/password-policies)
for more information on editing the default policy or creating your own.

## Adding an Administrative User

If you're setting things up for the first time, you're likely to be using the
default administrator account, the account of one of those famous Liferay
Administrators, *Test Test* or her cousin, *Joe Bloggs*. Because these are
default accounts, hackers know about them, so it's better to set up your own
administrator account. Add a user with your information, then give your user
account the same administrative rights as the default administrator's account: 

1.  Click the *Roles* link in the left navigation pane (in the *Edit User*
    page's *General* tab). This page of the form shows the Roles to which your
    account is currently assigned. No Roles appear by default (the User role
    does not appear since it can't be removed). 

2.  Click *Select* under Regular Roles and assign the Administrator Role to your
    user account. A dialog box pops up with a list of all the regular
    (portal-scoped) Roles in the portal. Select the Administrator Role from the
    list (click *Choose*). The dialog box disappears and the Role is added to
    the list of Roles associated with your account. You are now a portal
    administrator. Log out and then log back in with your own user account. 

| **Power Users:** Users are not assigned the Power User Role by default. The
| Power User Role grants more permissions than the User Role. If the User Role is
| sufficient for you, ignore the Power User Role. Alternatively, use it to provide
| a second level of User permissions and assign it to those Users. If there are
| certain custom permissions that you'd like all of your portal Users to have, you
| can grant these permissions to the User Role. You can also customize the default
| Roles a new User receives via *Default User Associations*. This is covered in
| the article on
| [Instance Settings](/documentation/user/-/knowledge_base/7-2/setting-up-a-virtual-instance).

In production, you should always delete or disable the default administrator
account to secure your portal.

## Gender

To collect data on users' genders, enable the binary gender field in the *Add
User* form or create a 
[custom field](/documentation/user/-/knowledge_base/7-2/custom-fields)
that meets your needs.

Enable the binary field by including the following in `portal-ext.properties`:

    `field.enable.com.liferay.portal.kernel.model.Contact.male=true`

## User Profile Pictures

Users have profile pictures. Administrative Users can upload images in the Edit
User form, and any User can update her own account information, including image,
from her personal site (*My account* &rarr; *Account Settings*). 

![Figure 1: Upload images for user avatars in the Edit User form.](../../../images/usrmgmt-ray-avatar.png)

If no image is explicitly uploaded for a User's profile picture, a default User
icon is assigned as the User avatar. By default the User's initials are
displayed (First Name then Last Name) over a random color.

![Figure 2: The default user profile picture is an icon with the user initials over a randomly colored bubble.](../../../images/users-default-user-image.png)

If the initials-based approach for generating User profile pictures isn't
suitable for your portal, disable the inclusion of Users' initials in the
default icons: 

1.  Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. 

2.  In the Platform section, click *Users* &rarr; *User Images*.

3.  Deselect *Use Initials for Default User Portrait*.

Now, instead of the default icon, which is a colorful circle containing the
user's initials, the icon is a gray circle containing the approximate shape of a
human being.

![Figure 3: If you disable the default initials-based profile picture, this icon is used instead.](../../../images/user-image-not-initials.png)

This is just the default. To override it with your own default image:

1.  Create at least one image that is a 100x100 px square. Place it somewhere on
    the application server's classpath. For example, in Tomcat you could place
    it in the `tomcat/webapps/ROOT/WEB-INF/classes` folder. 

2.  Set the following property in a `portal-ext.properties` file:

        image.default.user.portrait=image-filename-here.png

    This overrides the value of this 
    [portal property](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html):

        image.default.user.portrait=com/liferay/portal/dependencies/user_portrait.png

    | **NOTE:** If you are using the binary field to collect information on users'
    | genders (see above), then you'll have two default images to override. Set
    | these properties instead:
    | 
    |     image.default.user.female.portrait=image-filename.png
    |     image.default.user.male.portrait=image-filename.png

3.  Restart the application server.

| **Note:** There's a way to adjust which initials are displayed and in what
| order, so you can make the default user icon (with the user initials) work
| for your locale. These settings are configured in a
| [Language Settings module](/docs/7-2/frameworks/-/knowledge_base/f/using-liferays-localization-settings),
| so kidnap a friendly developer, give him a cup of coffee, and tell him the
| settings you want to change:
| 
| `lang.user.default.portrait=initials` sets the type of icon to use for avatars.
| The default value is *initials*. If set to initials, the next property
| configures which initials to display, and in what order. Alternatively, specify
| *image*, which gives you the same non-initials default image shown above.
| 
| `lang.user.initials.fields=first-name,last-name` determines which initials
| appear in the user portrait and in what order. The setting here only matters if
| `lang.user.default.portrait` is set to *initials*.  Valid values are first name,
| middle name, last name, with first and last name as the defaults.

## Numeric Screen Names

In prior versions, numeric user screen names were disabled out of the box via
the default portal property

    users.screen.name.allow.numeric=false

Other user management systems (LDAP, for example) did not have the same
restriction, which made importing users more difficult. Administrators first had
to set the  above property to `true` before importing and hope that no screen
names conflicted with site IDs. In @product-ver@, this property defaults to
`true` and there's no danger of numeric screen names conflicting with site IDs:

    users.screen.name.allow.numeric=true

This means you're free to set a user screen name to *24601*, or whatever other
number you can think of, and imports from systems that allow numeric screen
names go more smoothly. That's everything you need to know to take advantage of
this feature. Keep reading to understand what enabled the change.

Because users have personal sites, the URL to user *24601*'s personal site is

    http://localhost:8080/web/24601

Meanwhile, a default site URL to cleverly named *Test Site* is

    http://localhost:8080/web/test-site

There's no conflict here, but two conditions could easily lead to one:

1.  *Test Site*'s group ID matches the number chosen for the user's screen
    name. Each site has a unique numeric identifier in the database, called
    group ID. There's nothing stopping it from matching the user's numeric
    screen name, so it could easily be `24601` just like the hypothetical user
    above.

2.  A site administrator comes along and changes the site's friendly URL to
    match its `groupId`. Hello, URL conflict! Now the site's URL matches the
    user's URL:

        http://localhost:8080/web/24601

This conflict is no longer possible. In @product-ver@, a site's friendly URL
is not allowed to be numeric. See for yourself:

1.  Navigate to the site's *Configuration* &rarr; *Site Settings* &rarr; *Site URL*
    section.

2.  In the Friendly URL section, enter *24601* and save the form. A failure
    message appears if you don't have a user with the matching screen name:

        The friendly URL may conflict with another page.

    You'll see this failure message if there's an existing conflict with a user
    screen name:

        Please enter a unique friendly URL. Site [user-first-name user-last-name] has the same friendly URL.

Next, learn about collecting users in organizations.
