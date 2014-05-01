/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.pralleluniverse.ide.nebeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
            Runtime.getRuntime().exec("/Users/eitan/bin/killgradle.sh").waitFor();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
