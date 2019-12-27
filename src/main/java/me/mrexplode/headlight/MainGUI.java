package me.mrexplode.headlight;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
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
import javax.swing.SwingUtilities;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
    private JFrame instance;
    
    private JPanel workspacePanel;
    private JPanel controlPanel;
    private JPanel arena;
    private JTextField arenaWidth;
    private JLabel lblArenaWidth;
    private JTextField arenaLength;
    private JLabel lblArenaLength;
    private JButton btnSave;
    private JButton btnAspectRation;
    private JButton btnNewButton;

    /**
     * Create the frame.
     */
    public MainGUI() {
        instance = this;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setIconImages(getIcons());
        setTitle("HeadLight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 712, 400);
        setMinimumSize(new Dimension(712, 400));
        
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
        
        arenaWidth = new JTextField();
        arenaWidth.setColumns(10);
        
        lblArenaWidth = new JLabel("Arena width:");
        
        arenaLength = new JTextField();
        arenaLength.setColumns(10);
        
        lblArenaLength = new JLabel("Arena length:");
        
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Arena) arena).setWidth(Double.valueOf(arenaWidth.getText()));
                ((Arena) arena).setLength(Double.valueOf(arenaLength.getText()));
                System.out.println("Setting width (" + arenaWidth.getText() + ") and length (" + arenaLength.getText() + ")");
            }
        });
        
        btnAspectRation = new JButton("Actualize AR");
        btnAspectRation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arena a = (Arena) arena;
                double aspectRatio = a.getILength() / a.getIWidth();
                System.out.println("ar " + aspectRatio + " h" + (int) (instance.getHeight() * aspectRatio));
                //instance.setBounds(instance.getX(), instance.getY(), instance.getWidth(), (int) (instance.getHeight() * aspectRatio));
                instance.setSize(instance.getWidth(), (int) (instance.getHeight() * aspectRatio));
            }
        });
        btnAspectRation.setToolTipText("Actualize the window aspect ratio to the given values");
        
        btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Arena a = (Arena) arena;
                Fixture f = new Fixture(5, 6, 10, a.rMetricX, a.rMetricY);
                f.size = 10000;
                a.addFixture(f);
                System.out.println("metricX: " + a.rMetricX);
                System.out.println("metricY: " + a.rMetricY);
                
            }
        });
        GroupLayout gl_controlPanel = new GroupLayout(controlPanel);
        gl_controlPanel.setHorizontalGroup(
            gl_controlPanel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_controlPanel.createSequentialGroup()
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGap(4)
                            .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblArenaWidth)
                                .addComponent(lblArenaLength))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(arenaLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(arenaWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGap(60)
                            .addComponent(btnSave)))
                    .addContainerGap(10, Short.MAX_VALUE))
                .addGroup(gl_controlPanel.createSequentialGroup()
                    .addContainerGap(73, Short.MAX_VALUE)
                    .addComponent(btnAspectRation)
                    .addContainerGap())
                .addGroup(gl_controlPanel.createSequentialGroup()
                    .addContainerGap(45, Short.MAX_VALUE)
                    .addComponent(btnNewButton)
                    .addGap(42))
        );
        gl_controlPanel.setVerticalGroup(
            gl_controlPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_controlPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(arenaWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblArenaWidth))
                    .addGap(18)
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(arenaLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblArenaLength))
                    .addGap(18)
                    .addComponent(btnAspectRation)
                    .addGap(68)
                    .addComponent(btnNewButton)
                    .addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                    .addComponent(btnSave)
                    .addContainerGap())
        );
        controlPanel.setLayout(gl_controlPanel);
        
        arena = new Arena();
        GroupLayout gl_workspacePanel = new GroupLayout(workspacePanel);
        gl_workspacePanel.setHorizontalGroup(
            gl_workspacePanel.createParallelGroup(Alignment.TRAILING)
                .addComponent(arena, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );
        gl_workspacePanel.setVerticalGroup(
            gl_workspacePanel.createParallelGroup(Alignment.LEADING)
                .addComponent(arena, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
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
