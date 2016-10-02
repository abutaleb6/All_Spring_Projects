/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('MyApp.view.main.Main', {
    extend: 'Ext.panel.Panel',
    //xtype: 'app-main',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',

        'MyApp.view.main.MainController',
        'MyApp.view.main.MainModel',
        'appConstants',
        'Ext.tab.Panel',
        'Ext.tab.Tab',
        'Ext.toolbar.Toolbar',
        'Ext.toolbar.Spacer',
        'Ext.form.Label',
        'Ext.form.field.Date',
        'Ext.form.field.ComboBox',
        'Ext.grid.Panel',
        'Ext.grid.column.Boolean',
        'Ext.view.Table',
        'Ext.selection.CheckboxModel',
        'Ext.tree.Panel',
        'Ext.grid.filters.filter.Number',
        'Ext.grid.column.Date',
        'Ext.grid.filters.filter.Date',
        'Ext.grid.filters.filter.String',
        'Ext.grid.filters.filter.Boolean',
        'Ext.selection.CheckboxModel',
        'Ext.grid.filters.Filters',
        'Ext.grid.feature.Summary'
    ],
    controller: 'main',
    viewModel: {
        type: 'main'
    },
    autoShow: true,
    //height: 649,
    //id: 'myAppMain',
    //width: 899,
    layout: 'border',
    header: false,
    title: '',
    maximized: true,
    minimizable: true,

    items: [
        {
            xtype: 'tabpanel',
            region: 'center',
            layout: 'fit',
            //id: 'kycHome',
            margin: '5 0 0 0',
            title: 'Customer Home',
            activeTab: 0,
            tabPosition: 'bottom',
            items: [
                {
                    xtype: 'tabpanel',
                    //id: 'kycMainPanel',
                    layout: 'fit',
                    title: 'Customer Main',
                    reference: 'kycMainTabPanel',
                    tabConfig: {
                        //xtype: 'tab',
                        id: 'kycMainTabPanelId',
                        itemId: 'kycMainTabPanel'
                    },
                    padding: 5,
                    bodyPadding: 0,
                    items: [
                        {
                            xtype: 'panel',
                            //id: 'kycSearchPanel',
                            layout: 'fit',
                            title: 'Customer Search',
                            tabConfig: {
                                xtype: 'tab',
                                id: 'kycSearchTabPanelId',
                                itemId: 'kycSearchTabPanel'
                            },
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    itemId: 'KycStatus',
                                    layout: {
                                        type: 'hbox',
                                        padding: 5
                                    },
                                    items: [
                                        {
                                            xtype: 'tbspacer'
                                        },
                                        {
                                            xtype: 'label',
                                            text: 'Search Status',
                                            reference: 'kycStatusBar'
                                        }
                                    ]
                                },                              
                                {
                                    xtype: 'panel',
                                    dock: 'top',
                                    height: 130,
                                    //id: 'kycSearchProperties',
                                    itemId: 'kycSearchItemProperties',
                                    padding: '',
                                    layout: 'column',
                                    collapseDirection: 'top',
                                    collapsed: true,
                                    collapsible: true,
                                    padding: 1,
                                    title: 'Customer Search Properties',
                                    dockedItems: [
                                        {
                                            xtype: 'toolbar',
                                            dock: 'bottom',
                                            shadow: true,
                                            itemId: 'kycSearchTollbar',
                                            layout: {
                                                type: 'hbox',
                                                align: 'middle',
                                                pack: 'center'
                                            },
                                            items: [
                                                {
                                                    xtype: 'button',
                                                    padding: 3,
                                                    text: 'Search',
                                                    listeners: {
                                                        click: 'onClickSearch'
                                                    }
                                                },
                                                {
                                                    xtype: 'button',
                                                    padding: 3,
                                                    text: 'Clear',
                                                    listeners: {
                                                        click: 'onClickSearchClear'
                                                    }
                                                }
                                            ]
                                        }
                                    ],
                                    items: [
                                          {
                                            columnWidth:0.33,
                                            border : false,
                                            layout : 'anchor',
                                            items:[
                                                {
                                                    xtype: 'textfield',
                                                    padding: 5,
                                                    fieldLabel: 'Branch',
                                                    margin: '0 10 0 0',
                                                    reference: 'branch'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    padding: 5,
                                                    fieldLabel: 'Account No',
                                                    margin: '0 10 0 0',
                                                    reference: 'accNo'
                                                }
                                            ]
                                          },
                                          {
                                            columnWidth:0.33,
                                            border : false,
                                            layout : 'anchor',
                                            items:[
                                                {
                                                    xtype: 'datefield',
                                                    padding: 5,
                                                    fieldLabel: 'To Date',
                                                    margin: '0 10 0 0',
                                                    reference: 'toDate'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    padding: 5,
                                                    fieldLabel: 'Account Name',
                                                    margin: '0 10 0 0',
                                                    reference: 'accName'
                                                }
                                            ]
                                          },
                                          {
                                            columnWidth:0.33,
                                            border : false,
                                            layout : 'anchor',
                                            items:[
                                                {
                                                    xtype: 'datefield',
                                                    padding: 5,
                                                    fieldLabel: 'From Date',
                                                    margin: '0 10 0 0',
                                                    reference: 'frmDate'
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    padding: 5,
                                                    reference: 'searchType',
                                                    margin: '0 10 0 0',
                                                    fieldLabel: 'Search Setting',
                                                    name: 'nameSearchType',
                                                    value: 'EXACT',
                                                    displayField: 'nameSearchType',
                                                    forceSelection: true,
                                                    queryMode: 'local',
                                                    store: [
                                                        'EXACT',
                                                        'LIKE',
                                                        'SOUNDEX'
                                                    ]
                                                }
                                            ]
                                          }
                                    ]
                                },
                                {
                                    xtype: 'button',
                                    padding: 3,
                                    text: 'Add Customer',
                                    margin: '0 800 0 0',
                                    listeners: {
                                        click: 'onClickAddCustomerFormOpen'
                                    }
                                }
                            ],
                            items: [                                
                                {
                                    xtype: 'gridpanel',
                                    title: '<div style="margin-left:0px; font-size:12px; font-weight:lighter">Customer Live Search Pad</div>',
                                    //store: 'kycStore',
                                    selModel: {
                                        pruneRemoved: false
                                    },
                                    multiSelect: true,
                                    viewConfig: {
                                        trackOver: false
                                    },
                                    bind: {
                                        store: '{kycStore}'
                                    },
                                    loadMask:true,
                                    plugins: [
                                                {
                                                    ptype: 'gridfilters'
                                                }
                                            ],
                                    listeners: {
                                                    itemdblclick: 'loadDetailsView'
                                           },
                                    columns: [
                                        {xtype: 'gridcolumn', text: 'ID', width: 250, sortable: false, align: 'center', dataIndex : 'kycBankCustomerId', hidden: true},
                                        {xtype: 'gridcolumn', text: 'Branch ID', width: 80, sortable: false, align: 'center', dataIndex : 'externalBankId', hidden: true},
                                        {xtype: 'gridcolumn', text: 'Branch', width: 150, sortable: false, align: 'left', dataIndex : 'branchName', hidden: false,filter: {
                                                        type: 'string'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Last Status', width: 150, sortable: false, align: 'center', dataIndex : 'stateType',filter: {
                                                        type: 'list'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Last Checked',width: 130, sortable: false, align: 'center', dataIndex : 'lastChecked',filter: {
                                                        type: 'date'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Account No.', width: 200, sortable: false, align: 'center', dataIndex : 'customerAccountNo', hidden: false,filter: {
                                                        type: 'string'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Account Name', width: 250, sortable: false, align: 'left', dataIndex : 'customerName', hidden: false,filter: {
                                                        type: 'string'
                                                    }},

                                        {xtype: 'gridcolumn', text: 'Is Hit', width: 80, sortable: false, align: 'center', dataIndex : 'isOfacHit', hidden: true,filter: {
                                                        type: 'list'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Account Type', width: 120, sortable: false, align: 'left', dataIndex : 'cutomerAccountType',filter: {
                                                        type: 'list'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Account Status', width: 120, sortable: false, align: 'left', dataIndex : 'customerAccountStatus',filter: {
                                                        type: 'list'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Account Open Date', width: 130, sortable: false, align: 'left', dataIndex : 'accountOpenDate',filter: {
                                                        type: 'date'
                                                    }},

                                        {xtype: 'gridcolumn', text: 'Business Address', width: 250, sortable: false, align: 'left', dataIndex : 'customerBusinessAddress',filter: {
                                                        type: 'string'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Permanent Address', width: 250, sortable: false, align: 'left', dataIndex : 'customerPermanentAddress',filter: {
                                                        type: 'string'
                                                    }},

                                        {xtype: 'gridcolumn', text: 'Overridden On', width: 130, sortable: false, align: 'center', dataIndex : 'dateApproved',filter: {
                                                        type: 'date'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Overridden By', width: 120, sortable: false, align: 'center', dataIndex : 'approvedByName',filter: {
                                                        type: 'string'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Authorized on', width: 130, sortable: false, align: 'center', dataIndex : 'dateAuthorised',filter: {
                                                        type: 'date'
                                                    }},
                                        {xtype: 'gridcolumn', text: 'Authorized By', width: 120, sortable: false, align: 'center', dataIndex : 'authorisedByName',filter: {
                                                        type: 'string'
                                                    }},
                                        {   xtype: 'gridcolumn',
                                            text: 'Comment',
                                            width: 180,
                                            sortable: false,
                                            align: 'left',
                                            dataIndex : 'comment',
                                            field: {
                                                        xtype: 'textfield',
                                                   }
                                        }
                                    ],
                                    selModel: {
                                        selType: 'checkboxmodel'
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'bottom',
            //id: 'kycStatusBarId'

        }
    ],
    listeners: {
        //afterrender: 'onKycPanelRender'
    }


});