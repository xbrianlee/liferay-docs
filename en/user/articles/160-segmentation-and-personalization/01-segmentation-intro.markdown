---
header-id: segmentation-and-personalization
---

# Segmentation and Personalization

[TOC levels=1-4]

Liferay's Segmentation and Personalization shows the right content to the 
right people at the right time. It provides the tools you need to manage 
different audiences and dynamically provide personalized experiences for people 
using your site. For example, if you're creating a campaign to promote new 
financial service products, you need a way to display offers to customers who 
are likely to be interested in those offers. You don't want to display 
information on a basic free checking account for an "advanced" customer who 
carries a high balance across several types of accounts, but you do want to 
show that information to a visitor who entered the site through a landing page 
from a promotion at a local college. At the same time, you probably don't 
want to recommend options for optimizing retirement account contributions to 
the college student, but the other customer might be a great target for that 
campaign. By using data like user attributes or visitor interactions, you can
dynamically target relevant content to your site's guests.

## Defining Segments

The first part of the equation is defining the types of segments that you need. 
You can create Segments to capture every case. Segments are composed of
different criteria. In the previous example you might have a segment for *Free
Checking Account Prospects* that contains criteria based on user data, like
customers that don't currently have an open checking account; or based on user
behavior, like visitors who came to the site through specific channels. To learn
more about Segmentation options, see the 
[overview of the Segment editor](/docs/7-2/user/-/knowledge_base/u/the-segment-editor), practice
[creating basic Segments](/docs/7-2/user/-/knowledge_base/u/creating-user-segments),
or create more complicated 
[Segments with custom fields and session data](/docs/7-2/user/-/knowledge_base/u/creating-segments-with-custom-fields-and-session-data).

## Integrating Segments with Analytics Cloud

There are two different stories that User Segments can tell. One is targeting
content to specific audiences that encourages engagement and positive user
experiences. The other is defining groups of users and visitors to analyze their
behavior and interactions with your site. To tell the second story, you must
integrate with Analytics Cloud.

Analytics Cloud is a Liferay service that provides in-depth information on who
uses your site and how they use it. Analytics Cloud is a key component to fully
utilizing Segments and Personalization, since it enables you to see the full
picture of how users and visitors on your site behave and interact with
both standard and targeted content. You can learn more about this in 
[Using Analytics Cloud with User Segments](https://help.liferay.com/hc/en-us/articles/360029041751-Using-Analytics-Cloud-With-User-Segments).

## Personalizing Experiences

The most important piece of the puzzle isn't defining groups or analyzing user 
behavior. It's the final step of using the data to provide users and site 
visitors with the best possible experience, and driving campaigns and content 
engagement. If you strategically create segments, you can then use that to 
enhance user experiences, and make sure that users see content targeted to them.
Content Page Personalization and Content List Personalization are two key
aspects of this.

### Content Page Personalization

Content Page Personalization dynamically changes the page layout and 
content based on who is viewing the page. You can create *Experiences* for any 
[Content Page](/docs/7-2/user/-/knowledge_base/u/creating-content-pages) which
provide different text, images, widgets,  and even different layouts based on
the segment criteria of the user viewing  the page. You can see a step by step
demonstration of this in
[Content Page Personalization](/docs/7-2/user/-/knowledge_base/u/content-page-personalization).

### Content Set Personalization

[Content Sets](/docs/7-2/user/-/knowledge_base/u/content-sets) organize and
display content. Content Set Personalization provides dynamic selection of
Content Sets based on User Segments. This means the Content Set which displays
in a given context is determined by their segment criteria. For example, you
could use a content list to display "featured" articles at the top of a page.
Then you could create Segments containing users who should receive more
specialized content, rather than the default. Those Segments would then see
content personalized to their interest rather than the default. You can see  a
step by step demonstration of this in
[Content Set Personalization](/docs/7-2/user/-/knowledge_base/u/content-set-personalization).
