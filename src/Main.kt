import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Main : JFrame() {
    private var contentPane: JPanel
    private var mine = Mine(9,9)
    var btn: Array<Array<JButton?>> =
        Array<Array<JButton?>>(9) { arrayOfNulls<JButton>(9) }
    var count = 0
    fun initGame() {
        contentPane.removeAll()
        setMine()
        setButton()
    }

    fun setMine() {
        //set some of mines in random
        val random = Random()
        var count = 0
        while (count < 10) {
            val randomX = random.nextInt(9)
            val randomY = random.nextInt(9)
            if (mine.getMine(randomX, randomY) === false) {
                mine.setMine(randomX, randomY)
                count++
            }
        }
    }

    fun setButton() {
        for (i in 0..8) {
            for (j in 0..8) {
                btn[i][j] = JButton()
                contentPane.add(btn[i][j])
            }
        }
        for (i in 0..8) {
            for (j in 0..8) {
                val button: JButton? = btn[i][j]
                button?.addMouseListener(object : MouseListener {
                    override fun mouseEntered(arg0: java.awt.event.MouseEvent) {
                        // TODO Auto-generated method stub
                    }

                    override fun mouseExited(arg0: java.awt.event.MouseEvent) {
                        // TODO Auto-generated method stub
                    }

                    override fun mousePressed(e: java.awt.event.MouseEvent) {
                        // TODO Auto-generated method stub
                        if (e.getModifiers() == java.awt.event.MouseEvent.BUTTON3_MASK) {
                            setFlags(i, j)
                        } else {
                            mekuru(i, j)
                        }
                    }

                    override fun mouseReleased(arg0: java.awt.event.MouseEvent) {
                        // TODO Auto-generated method stub
                    }

                    override fun mouseClicked(e: java.awt.event.MouseEvent) {
                        // TODO Auto-generated method stub
                    }
                })
            }
        }
    }

    private fun mekkutteru(x: Int, y: Int): Boolean {
        val button: JButton? = btn[x][y]
        return button?.getBackground() === java.awt.Color.blue
    }

    private fun owattakana(): Boolean {
        return if (count == 71) {
            true
        } else {
            false
        }
    }

    private fun owattayo() {
        JOptionPane.showConfirmDialog(
            null,
            "Success!",
            "Do you want to start the new game?",
            JOptionPane.NO_OPTION
        )
    }

    fun mekuru(x: Int, y: Int) {
        val button: JButton? = btn[x][y]
        if (mekkutteru(x, y)) {
            return
        }
        count++
        if (mine.getMine(x, y) === true) {
            button?.setOpaque(true)
            button?.setBackground(java.awt.Color.blue)
            val mine = ImageIcon("./src/mine.png")
            btn[x][y]?.setIcon(mine)
            val ans: Int = JOptionPane.showConfirmDialog(
                null,
                "Start new game",
                "GAME OVER",
                JOptionPane.YES_NO_OPTION
            )
            when (ans) {
                JOptionPane.YES_OPTION -> {
                    val frame = Main()
                    frame.setVisible(true)
                    frame.setTitle("MineSweeper")
                    dispose()
                }
                JOptionPane.NO_OPTION -> System.exit(0)
            }
        } else {
            btn[x][y]?.setOpaque(true)
            btn[x][y]?.setBackground(java.awt.Color.blue)
            button?.setBorder(null)
            if (mine.countMine(x, y) > 0) {
                btn[x][y]?.setText(java.lang.String.valueOf(mine.countMine(x, y)))
            } else {
                for (i in x - 1..x + 1) {
                    if (i < 0 || i >= mine.width) {
                        continue
                    }
                    for (j in y - 1..y + 1) {
                        if (j < 0 || j >= mine.height) {
                            continue
                        }
                        mekuru(i, j)
                    }
                }
            }
        }
        if (owattakana()) {
            owattayo()
        }
    }

    fun setFlags(x: Int, y: Int) {
        val flag = ImageIcon("./src/flag.png")
        btn[x][y]?.setIcon(flag)
    }

    companion object {
        /**
         * Launch the application.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            java.awt.EventQueue.invokeLater(Runnable {
                try {
                    val frame = Main()
                    frame.setVisible(true)
                    frame.setTitle("MineSweeper")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
        }
    }

    /**
     * Create the frame.
     */
    init {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        setBounds(100, 100, 400, 400)
        contentPane = JPanel()
        contentPane.setLayout(java.awt.GridLayout(9, 9))
        setContentPane(contentPane)
        initGame()
    }
}