package controller;

import bean.DanhMucBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import view.CachLyJPanel;
import view.HoKhauJPanel;
import view.KhaiBaoJPanel;
import view.KiemTraJPanel;
import view.NhanKhauJPanel;
import view.ThongKeCachLyJPanel;
import view.ThongKeJFrame;
import view.ThongKeTestJPanel;
import view.TrangChuJPanel;

/**
 *
 * @author admin
 */
public class ChuyenManHinhController {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;
    private Color colorBackGround = new Color(132, 180, 203);

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;
        private boolean setByThongKeListerner;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "NhanKhau":
                    node = new NhanKhauJPanel();
                    break;
                case "KiemTra":
                    node = new KiemTraJPanel();
                    break;
                case "CachLy":
                    node = new CachLyJPanel();
                    break;
                case "HoKhau":
                    node = new HoKhauJPanel();
                    break;
                case "KhaiBao":
                    node = new KhaiBaoJPanel();
                    break;
                case "ThongKe":
                    ThongKeJFrame frame = new ThongKeJFrame(root);
                    frame.setTitle("Thông Tin Cách Ly Covid");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    // to do: add statement and code to let user choose which type of 
                    break;
                default:
                    break;
            }
            if (node != null) {
                root.removeAll();
                root.setLayout(new BorderLayout());
                root.add(node);
                root.validate();
                root.repaint();
            }
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(colorBackGround);
                jlbItem.setBackground(colorBackGround);
            }
        }

    }

    private void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            } else {
                item.getJpn().setBackground(colorBackGround);
                item.getJlb().setBackground(colorBackGround);
            }
        }
    }
}
