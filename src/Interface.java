import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Interface extends JFrame {
    Koneksi koneksi = new Koneksi();
    String dbTabel = "users";
    //label
    JLabel lJudul = new JLabel("Form Login & Register");
    JLabel lUsername1 = new JLabel("Username");
    JLabel lPassword1 = new JLabel("Password");
    JLabel lUsername2 = new JLabel("Username");
    JLabel lPassword2 = new JLabel("Password");
    //text field
    JTextField tfUsername1 = new JTextField();
    JTextField tfPassword1 = new JTextField();
    JTextField tfUsername2 = new JTextField();
    JTextField tfPassword2 = new JTextField();

    JButton btnLogin = new JButton("Login");
    JButton btnDaftar = new JButton("Daftar");

    public Interface(){
        setTitle("Tugas 4");
        setSize(560, 250);
        setLayout(null);

        add(lJudul);
        add(lUsername1);
        add(lPassword1);
        add(lUsername2);
        add(lPassword2);

        add(tfUsername1);
        add(tfPassword1);
        add(tfUsername2);
        add(tfPassword2);

        add(btnLogin);
        add(btnDaftar);

        lJudul.setBounds(200,10,200,20);

        lUsername1.setBounds(20, 60, 100, 20);
        tfUsername1.setBounds(90, 60, 150, 20);

        lPassword1.setBounds(20,90,100,20);
        tfPassword1.setBounds(90,90,150,20);

        lUsername2.setBounds(290,60,100,20);
        tfUsername2.setBounds(360,60,150,20);

        lPassword2.setBounds(290,90,100,20);
        tfPassword2.setBounds(360,90,150,20);

        btnLogin.setBounds(90,130,80,20);
        btnDaftar.setBounds(360,130,80,20);

        //aksi tombol daftar
        btnDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;
                try{
                    String query = "Select * from `users` where " + "username = '" + tfUsername2.getText() + "'";
                    koneksi.statement = koneksi.konek.createStatement();
                    ResultSet resultSet = koneksi.statement.executeQuery(query);
                    if (resultSet.next()){
                        JOptionPane.showMessageDialog(null,"Username Sudah digunakan !!");
                    } else {
                        if(tfUsername2.getText().equals("") || tfPassword2.getText().equals("")) {
                            JOptionPane.showMessageDialog(null,"Tidak boleh ada field yang kosong!");
                        } else {
                            String sql = "insert into " + dbTabel+ " VALUES ('"+id +"','"+tfUsername2.getText()+"','"+tfPassword2.getText()+"')";

                            koneksi.statement = koneksi.konek.createStatement();
                            koneksi.statement.executeUpdate(sql);

                            JOptionPane.showMessageDialog(null,"Berhasil Mendaftarkan User !!");
//                            tfUsername2.setText("");
//                            tfPassword2.setText("");
                        }
                    }

                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Error!! \n" + exception.getMessage());
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "Select * from `users` where " + "username = '" + tfUsername1.getText() + "'";
                    koneksi.statement = koneksi.konek.createStatement();
                    ResultSet resultSet = koneksi.statement.executeQuery(query);
                    if (resultSet.next()){
                        if (tfPassword1.getText().equals(resultSet.getString("password"))){
                            JOptionPane.showMessageDialog(rootPane,"Berhasil Login");
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Password salah");
//                            tfPassword1.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane,"User Tidak DItemukan");
//                        tfUsername1.setText("");
//                        tfPassword1.setText("");
                        tfUsername1.requestFocus();
                    }
                }catch (Exception exc){
                    JOptionPane.showMessageDialog(rootPane, "gagal");
                }
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
