/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.pralleluniverse.ide.nebeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Build",
        id = "co.pralleluniverse.ide.nebeans.RunShellCommand"
)
@ActionRegistration(
        iconBase = "co/pralleluniverse/ide/nebeans/Delete.png",
        displayName = "#CTL_RunShellCommand"
)
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0),
    @ActionReference(path = "Toolbars/Build", position = 500),
    @ActionReference(path = "Shortcuts", name = "S-F10")
})
@Messages("CTL_RunShellCommand=KillGradle")
public final class RunShellCommand implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Process p = Runtime.getRuntime().exec("pgrep -f gradle");
            runKillChildrenForEveryLine(p.getInputStream());
            p.waitFor();
        } catch (IOException | InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static void runKillChildrenForEveryLine(InputStream is) throws InterruptedException, IOException {
        String pid;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((pid = br.readLine()) != null) {
                // kill all the childeren of the pid
                Runtime.getRuntime().exec("pkill -P " + pid).waitFor();
            }
        }
    }
}
