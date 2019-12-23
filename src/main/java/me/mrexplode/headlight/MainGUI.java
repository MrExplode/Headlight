package me.mrexplode.headlight;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JToolBar;


public class MainGUI extends JFrame {
    private JPanel workspacePanel;
    private JPanel controlPanel;

    /**
     * Create the frame.
     */
    public MainGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setIconImages(getIcons());
        setTitle("HeadLight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setMinimumSize(new Dimension(600, 400));
        
        workspacePanel = new JPanel();
        workspacePanel.setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        controlPanel = new JPanel();
        controlPanel.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(workspacePanel, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(controlPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addComponent(workspacePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                    .addContainerGap())
        );
        GroupLayout gl_workspacePanel = new GroupLayout(workspacePanel);
        gl_workspacePanel.setHorizontalGroup(
            gl_workspacePanel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 366, Short.MAX_VALUE)
        );
        gl_workspacePanel.setVerticalGroup(
            gl_workspacePanel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 339, Short.MAX_VALUE)
        );
        workspacePanel.setLayout(gl_workspacePanel);
        getContentPane().setLayout(groupLayout);
    }

    private List<Image> getIcons() {
        List<Image> list = new ArrayList<Image>();
        int[] sizes = new int[] {32, 64, 256};
        for (int i : sizes) {
            try {
                list.add(ImageIO.read(this.getClass().getResource("/icons/icon" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
