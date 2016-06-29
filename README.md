##Prompt Henplus Plug-In##

This plugin allows you to customize the command prompt for henplus. Currently it really only adds a status of whether the session is in auto-commit mode or not.

###Easy Setup###

Simply put `prompt-henplus-plugin.jar` in to the CLASSPATH of `henplus`, generally in the `share/henplus` folder somewhere.

Start `henplus` and register the plugin. Use the `plug-in` command for this. This only needs to be done once, and will be persisted.

     Hen*Plus> plug-in org.fakebelieve.henplus.plugins.prompt.PromptCommand

###Usage###

When enabled, the plugin will automatically change the command prompt to the format

    session-name!auto-commit>
    
Where auto-commit is the character
    
    A   - auto-commit enabled
    N   - auto-commit disabled
    -   - auto-commit status unknown
    