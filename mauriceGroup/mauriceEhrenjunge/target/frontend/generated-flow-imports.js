const div = document.createElement('div');
div.innerHTML = '<custom-style><style include="lumo-color lumo-typography"></style></custom-style>';
document.head.insertBefore(div.firstElementChild, document.head.firstChild);
document.documentElement.setAttribute('theme', 'dark');

function addCssBlock(block) {
 const tpl = document.createElement('template');
 tpl.innerHTML = block;
 document.head.appendChild(tpl.content);
}
import $css_0 from 'Frontend/styles/shared.css';
addCssBlock(`<custom-style><style>${$css_0}</style></custom-style>`);
import $css_1 from 'Frontend/styles/views/dashboard-view.css';
addCssBlock(`<custom-style><style>${$css_1}</style></custom-style>`);
import $css_2 from 'xterm/css/xterm.css';
addCssBlock(`<custom-style><style>${$css_2}</style></custom-style>`);
import $css_3 from 'Frontend/styles/views/main/main-view.css';
addCssBlock(`<custom-style><style>${$css_3}</style></custom-style>`);
import $css_4 from 'Frontend/styles/views/error/error-view.css';
addCssBlock(`<custom-style><style>${$css_4}</style></custom-style>`);
import $css_5 from 'Frontend/styles/views/login-view.css';
addCssBlock(`<custom-style><style>${$css_5}</style></custom-style>`);

import '@vaadin/flow-frontend/contextMenuConnector-es6.js';
import '@vaadin/flow-frontend/fc-xterm/clipboard-feature.js';
import '@vaadin/flow-frontend/fc-xterm/console-feature.js';
import '@vaadin/flow-frontend/fc-xterm/fit-feature.js';
import '@vaadin/flow-frontend/fc-xterm/xterm.js';
import '@vaadin/flow-frontend/flow-component-renderer.js';
import '@vaadin/flow-frontend/menubarConnector.js';
import '@vaadin/vaadin-app-layout/theme/lumo/vaadin-app-layout.js';
import '@vaadin/vaadin-app-layout/theme/lumo/vaadin-drawer-toggle.js';
import '@vaadin/vaadin-board/vaadin-board-row.js';
import '@vaadin/vaadin-board/vaadin-board.js';
import '@vaadin/vaadin-button/theme/lumo/vaadin-button.js';
import '@vaadin/vaadin-confirm-dialog/theme/lumo/vaadin-confirm-dialog.js';
import '@vaadin/vaadin-context-menu/theme/lumo/vaadin-context-menu.js';
import '@vaadin/vaadin-login/theme/lumo/vaadin-login-overlay.js';
import '@vaadin/vaadin-lumo-styles/color.js';
import '@vaadin/vaadin-lumo-styles/icons.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/typography.js';
import '@vaadin/vaadin-menu-bar/theme/lumo/vaadin-menu-bar.js';
import '@vaadin/vaadin-notification/theme/lumo/vaadin-notification.js';
import '@vaadin/vaadin-ordered-layout/theme/lumo/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-ordered-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/vaadin-tabs/theme/lumo/vaadin-tab.js';
import '@vaadin/vaadin-tabs/theme/lumo/vaadin-tabs.js';
import '@vaadin/flow-frontend/contextMenuConnector.js';
import 'Frontend/styles/shared-styles.js';
var scripts = document.getElementsByTagName('script');
var thisScript;
var elements = document.getElementsByTagName('script');
for (var i = 0; i < elements.length; i++) {
    var script = elements[i];
    if (script.getAttribute('type')=='module' && script.getAttribute('data-app-id') && !script['vaadin-bundle']) {
        thisScript = script;break;
     }
}
if (!thisScript) {
    throw new Error('Could not find the bundle script to identify the application id');
}
thisScript['vaadin-bundle'] = true;
if (!window.Vaadin.Flow.fallbacks) { window.Vaadin.Flow.fallbacks={}; }
var fallbacks = window.Vaadin.Flow.fallbacks;
fallbacks[thisScript.getAttribute('data-app-id')] = {}
fallbacks[thisScript.getAttribute('data-app-id')].loadFallback = function loadFallback(){
   return import('./generated-flow-imports-fallback.js');
}