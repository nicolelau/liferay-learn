# Adding a Page to a Site

The steps below describe how to add a new page to your Site and configure the general options. To learn how to configure the remaining page settings, see [Page Configuration UI Reference](../page-settings/page-configuration-ui-reference.md), or see [Configuring Page Sets](../page-settings/configuring-page-sets.md) to configure options for a [Page Set](../understanding-pages/understanding-pages.md#page-sets).

## Adding a New Page

Follow these steps to add a page:

1. Navigate to your desired Site by using the [Site Menu](../../../getting-started/navigating-dxp.md#site-menu).

1. Go to *Site Builder* &rarr; *Pages* under the Site Menu.

1. On the Pages page, click the *Add* button (![Add](../../../images/icon-add.png)) to create a top-level page.

    ![The Pages screen lets you edit your Site pages as a whole.](./adding-a-page-to-a-site/images/01.png)

    ```tip::
       Alternatively, since Liferay DXP 7.3, you can click the page tree icon next to the Site name to open the Page Tree Menu. You can use the Page Tree Menu to quickly find a page by keyword in the search bar, navigate to a page by selecting it, or even add pages through the Actions Menu.
    ```

1. Click the *Blank* [Master Page Template](../defining-headers-and-footers/master-page-templates.md) to start a [Content Page](../understanding-pages/understanding-pages.md#page-types) from scratch, select a [custom Master Page Template](../defining-headers-and-footers/creating-a-master-page-template.md) to create a Content Page based on a template, or select [another page type](../understanding-pages/other-page-types.md) under the *Other* heading, such as a [Widget Page](../understanding-pages/understanding-pages.md#widget-pages), or a Page Template listed under the *Global Template* menu.

    ![You must select a page type when adding pages.](./adding-a-page-to-a-site/images/04.png)

1. Enter a Name and configure the remaining settings for the page.

1. Click *Save* to create the page.

    ```tip::
       Creating a page by default also adds it to any Navigation Menus that are configured to have new pages added to them. See `Configuring Site Navigation <../../04-site-navigation/README.md>`_ for more information.
    ```

## Adding a Child Page

To add a child page through the Page Tree Menu, follow these steps:

1. Open the Product Menu and click the (![icon-page-tree](../../../images/icon-page-tree.png)) icon next to the Site name to open the Page Tree Menu.
1. Select *Add Child Page* from the Actions Menu next to an existing page to create a nested child page for it.

    ![Adding a Child Page using the Page Tree menu.](adding-a-page-to-a-site/images/05.png)

1. Follow the standard steps for creating a page, as outlined [above](#adding-a-new-page).

To add a child page through the Pages screen, follow these steps:

1. Open the Product Menu and go to *Site Builder* &rarr; *Pages* under your Site's menu.
1. Click the *Add* button (![Add](../../../images/icon-add-app.png)) next to an existing page. You can also click the *Add* button (![Add](../../../images/icon-add.png)) in the top right of the Pages page to create a top-level page.
1. Follow the standard steps for creating a page, as outlined above.

## Related Information

* [Understanding Pages](../understanding-pages/understanding-pages.md)
* [Content Page Overview](../building-and-managing-content-pages/content-pages-overview.md)
