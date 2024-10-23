package carlvbn.raytracing;

import carlvbn.raytracing.gui.AnimationPanel;
import carlvbn.raytracing.gui.SettingsPanel;
import carlvbn.raytracing.gui.Viewport;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Ray Tracing");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JDialog settingsDialog = new JDialog(frame, "Settings");
        Viewport viewport = new Viewport(frame, settingsDialog);
        frame.add(viewport);

        try {
            frame.setIconImage(new ImageIcon(viewport.getClass().getResource("/res/icon.png")).getImage());
        } catch (Exception e) {
            System.err.println("Failed to load icon");
            e.printStackTrace();
        }

        frame.setVisible(true);

        JDialog animationDialog = new JDialog(frame, "Animation");

        SettingsPanel settingsPanel = new SettingsPanel(viewport, animationDialog);

        animationDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        animationDialog.setSize(300, 150);
        animationDialog.add(new AnimationPanel(viewport, settingsPanel));
        animationDialog.setLocationRelativeTo(frame);

        settingsDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        settingsDialog.setSize(350, 600);
        settingsDialog.add(settingsPanel);
        settingsDialog.setVisible(true);

        viewport.runMainLoop();
    }
}
