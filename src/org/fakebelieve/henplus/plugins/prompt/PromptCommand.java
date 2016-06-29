/*
 * This is free software, licensed under the Gnu Public License (GPL)
 * get a copy from <http://www.gnu.org/licenses/gpl.html>
 */
package org.fakebelieve.henplus.plugins.prompt;

import henplus.*;
import henplus.event.ExecutionListener;

import java.sql.SQLException;
import java.util.SortedSet;

public final class PromptCommand extends AbstractCommand {

    private static final String COMMAND_PROMPT = "set-prompt";

    /**
     *
     */
    public PromptCommand() {
        registerPromptUpdater(HenPlus.getInstance().getDispatcher());
    }

    public void registerPromptUpdater(final CommandDispatcher dispatcher) {
        dispatcher.addExecutionListener(new ExecutionListener() {

            @Override
            public void beforeExecution(final SQLSession session, final String command) {
            }

            @Override
            public void afterExecution(SQLSession currentSession, final String command, final int result) {
                currentSession = HenPlus.getInstance().getCurrentSession();
                String currentSessionName = null;
                String autoCommit = "-";

                if (currentSession != null) {
                    SessionManager sessionManager = HenPlus.getInstance().getSessionManager();
                    SortedSet<String> sessionNames = sessionManager.getSessionNames();
                    for (String sessionName : sessionNames) {
                        SQLSession session = sessionManager.getSessionByName(sessionName);
                        if (currentSession.equals(session)) {
                            currentSessionName = sessionName;
                            break;
                        }
                    }

                    try {
                        if (currentSession.getConnection() != null) {
                            autoCommit = currentSession.getConnection().getAutoCommit() ? "A" : "N";
                        }
                    } catch (SQLException e) {
                        // IGNORE
                    }

                }

                if (currentSessionName != null) {

                    HenPlus.getInstance().setPrompt(currentSessionName + "!" + autoCommit + "> ");
                }
                //System.out.println("BING BING BING - " + currentSessionName);
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#getCommandList()
     */
    @Override
    public String[] getCommandList() {
        return new String[]{COMMAND_PROMPT};
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#participateInCommandCompletion()
     */
    @Override
    public boolean participateInCommandCompletion() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#execute(henplus.SQLSession, java.lang.String, java.lang.String)
     */

    @Override
    public int execute(SQLSession session, String command, String parameters) {
        int result = SUCCESS;

        // required: session
        if (session == null) {
            HenPlus.msg().println("You need a valid session for this command.");
            return EXEC_FAILED;
        }

        if (command.equals(COMMAND_PROMPT)) {
            if (parameters == null || parameters.isEmpty()) {
            } else {
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#isComplete(java.lang.String)
     */
    @Override
    public boolean isComplete(String command) {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#requiresValidSession(java.lang.String)
     */
    @Override
    public boolean requiresValidSession(String cmd) {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#shutdown()
     */
    @Override
    public void shutdown() {
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#getShortDescription()
     */
    @Override
    public String getShortDescription() {
        return "show/customize command prompt";
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#getSynopsis(java.lang.String)
     */
    @Override
    public String getSynopsis(String cmd) {
        return COMMAND_PROMPT + " " + " <prompt>";
    }

    /*
     * (non-Javadoc)
     * @see henplus.Command#getLongDescription(java.lang.String)
     */
    @Override
    public String getLongDescription(String cmd) {
        return "\tView or customize the command prompt\n"
                + "\n"
                + "\tTo view current prompt\n"
                + "\t\t" + COMMAND_PROMPT + ";\n"
                + "\tTo change the prompt\n"
                + "\t\t" + COMMAND_PROMPT + " <prompt>;\n"
                + "\n";
    }
}
