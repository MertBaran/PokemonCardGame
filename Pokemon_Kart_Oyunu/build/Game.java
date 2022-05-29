package build;

import game.Pokemon;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Game extends javax.swing.JFrame {

    public boolean debug = false;
    public int sira = 0;
    public int kartBirHasar = 0;
    public int kartIkiHasar = 0;
    public int destedenCekmeSayisi = 0;
    public ArrayList<Pokemon> secilen = new ArrayList();
    CreaterClass gameProperties = new CreaterClass();

    public Game() {
        initComponents();
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);

    }

    public void ScoreBoard() {
        if (gameProperties.getGameType() == 0) {
            playerOneName.setText(gameProperties.getComputer().getOyuncuAdi());
            playerOneScore.setText(Integer.toString(gameProperties.getComputer().getSkor()));
            System.out.println("Yeni Oyun Başlıyor");
        } else if (gameProperties.getGameType() == 1) {
            playerOneName.setText(gameProperties.getPlayer().getOyuncuAdi());
            playerOneScore.setText(Integer.toString(gameProperties.getPlayer().getSkor()));
            System.out.println("Yeni Oyun Başlıyor.\nOyuncu Adı: " + gameProperties.getPlayer().getOyuncuAdi());
        }

        playerTwoName.setText(gameProperties.getBot().getOyuncuAdi());
        playerTwoScore.setText(Integer.toString(gameProperties.getBot().getSkor()));
        gameProperties.cards();

        debugCardList.setContentAreaFilled(false);
        debugCardList.setBorder(null);
        player4.setVisible(false);
        bilgisayar4.setVisible(false);
        player5.setVisible(false);
        bilgisayar5.setVisible(false);
        if (gameProperties.getGameType() == 1) {
            botNextRaundBtn.setVisible(false);
        } else if (gameProperties.getGameType() == 0) {
            botNextRaundBtn.setVisible(true);
        }

    }

    public void raundScoreBoard() {
        if (gameProperties.getGameType() == 0) {
            playerOneName.setText(gameProperties.getComputer().getOyuncuAdi());
            playerOneScore.setText(Integer.toString(gameProperties.getComputer().getSkor()));
        } else if (gameProperties.getGameType() == 1) {
            playerOneName.setText(gameProperties.getPlayer().getOyuncuAdi());
            playerOneScore.setText(Integer.toString(gameProperties.getPlayer().getSkor()));
        }

        playerTwoName.setText(gameProperties.getBot().getOyuncuAdi());
        playerTwoScore.setText(Integer.toString(gameProperties.getBot().getSkor()));
    }

    public void oyun(Pokemon kart) {

        if (gameProperties.getGameType() == 1) {

            if (sira == 0) {
                secilen.add(kart);
                //gameBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(kart.isImage())));
                gameBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
                kartBirHasar = kart.HasarPuaniGoster();
                kart.setKartKullanildiMi(true);
                System.out.println("[Player]\nKart:=>" + kart.getPokemonAdi() + "\nHasar=>" + kartBirHasar + "\nKart Kullanıldı mı?=>" + kart.isKartKullanildiMi() + "\n");
                sira = 1;

                oyun(gameProperties.bot.kartSec());

            } else if (sira == 1) {
                secilen.add(kart);

                int kartNo = 99;
                for (int i = 0; i < gameProperties.getBot().kartListesi().size(); i++) {
                    if (kart.getPokemonAdi().equals(gameProperties.getBot().kartListesi().get(i).getPokemonAdi())) {
                        kartNo = i;
                    }
                }

                gameBoard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
                //gameBoard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(kart.isImage())));
                kartIkiHasar = kart.HasarPuaniGoster();
                kart.setKartKullanildiMi(true);

                System.out.println("[Bilgisayar 1]\nKart:=>" + kart.getPokemonAdi() + "\nHasar=>" + kartIkiHasar + "\nKart Kullanıldı mı?=>" + kart.isKartKullanildiMi() + "\n");
                System.out.println("---------RAUND SONU---------");
                switch (kartNo) {
                    case 0:
                        bilgisayar1.setVisible(false);
                        break;
                    case 1:
                        bilgisayar2.setVisible(false);
                        break;
                    case 2:
                        bilgisayar3.setVisible(false);
                        break;
                    case 3:
                        bilgisayar4.setVisible(false);
                        break;
                    case 4:
                        bilgisayar5.setVisible(false);
                        break;
                    default:
                        break;
                }

                sira = 3;

                Timer saat = new Timer();
                TimerTask gorev;
                gorev = new TimerTask() {
                    @Override
                    public void run() {
                        sira = 0;

                        gameBoard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(secilen.get(1).isImage())));
                        gameBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(secilen.get(0).isImage())));
                        saat.cancel();
                        secilen.removeAll(secilen);
                        if (kartBirHasar > kartIkiHasar) {
                            gameProperties.getPlayer().setSkor(5);
                            RaundStatus.setText("Son Raundu Kazanan: " + gameProperties.getPlayer().getOyuncuAdi());
                            showMessageDialog(null, "Raundu Kazanan: " + gameProperties.getPlayer().getOyuncuAdi());
                        } else if (kartBirHasar < kartIkiHasar) {
                            gameProperties.getBot().setSkor(5);
                            RaundStatus.setText("Son Raundu Kazanan: " + gameProperties.getBot().getOyuncuAdi());
                            showMessageDialog(null, "Raundu Kazanan: " + gameProperties.getBot().getOyuncuAdi());
                        } else if (kartBirHasar == kartIkiHasar) {
                            RaundStatus.setText("Son Raund Berabere");
                            showMessageDialog(null, "Raundu BERABERE");
                        }

                        raundScoreBoard();
                        DestedenCek();
                        destedenCekmeSayisi += 1;
                    }
                };
                int sure = 1000;
                saat.schedule(gorev, sure);

            }

        } else if (gameProperties.getGameType() == 0) {

            if (sira == 0) {
                secilen.add(kart);
                gameBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
                kartBirHasar = kart.HasarPuaniGoster();
                kart.setKartKullanildiMi(true);
                System.out.println("[Bilgisayar 2]\nKart=>" + kart.getPokemonAdi() + "\nHasar=>" + kartBirHasar + "\nKart Kullanıldı mı?=>" + kart.isKartKullanildiMi() + "\n");
                int kartNo = 99;
                for (int i = 0; i < gameProperties.getBot().kartListesi().size(); i++) {
                    if (kart.getPokemonAdi().equals(gameProperties.getComputer().kartListesi().get(i).getPokemonAdi())) {
                        kartNo = i;
                    }
                }
                switch (kartNo) {
                    case 0:
                        player1.setVisible(false);
                        break;
                    case 1:
                        player2.setVisible(false);
                        break;
                    case 2:
                        player3.setVisible(false);
                        break;
                    case 3:
                        player4.setVisible(false);
                        break;
                    case 4:
                        player5.setVisible(false);
                        break;
                    default:
                        break;
                }
                sira = 1;

                oyun(gameProperties.bot.kartSec());

            } else if (sira == 1) {
                secilen.add(kart);
                int kartNo = 99;
                for (int i = 0; i < gameProperties.getBot().kartListesi().size(); i++) {
                    if (kart.getPokemonAdi().equals(gameProperties.getBot().kartListesi().get(i).getPokemonAdi())) {
                        kartNo = i;
                    }
                }
                gameBoard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
                kartIkiHasar = kart.HasarPuaniGoster();
                kart.setKartKullanildiMi(true);

                System.out.println("[Bilgisayar 1]\nKart=>" + kart.getPokemonAdi() + "\nHasar=>" + kartIkiHasar + "\nKart Kullanıldı mı?=>" + kart.isKartKullanildiMi() + "\n");
                System.out.println("---------RAUND SONU---------");
                switch (kartNo) {
                    case 0:
                        bilgisayar1.setVisible(false);
                        break;
                    case 1:
                        bilgisayar2.setVisible(false);
                        break;
                    case 2:
                        bilgisayar3.setVisible(false);
                        break;
                    case 3:
                        bilgisayar4.setVisible(false);
                        break;
                    case 4:
                        bilgisayar5.setVisible(false);
                        break;
                    default:
                        break;
                }

                sira = 3;
                Timer saat = new Timer();
                TimerTask gorev;
                gorev = new TimerTask() {
                    @Override
                    public void run() {
                        sira = 0;
                        gameBoard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(secilen.get(1).isImage())));
                        gameBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(secilen.get(0).isImage())));
                        saat.cancel();
                        secilen.removeAll(secilen);
                        if (kartBirHasar > kartIkiHasar) {
                            gameProperties.getComputer().setSkor(5);
                            RaundStatus.setText("Son Raundu Kazanan: " + gameProperties.getComputer().getOyuncuAdi());
                            showMessageDialog(null, "Raundu Kazanan: " + gameProperties.getComputer().getOyuncuAdi());
                        } else if (kartBirHasar < kartIkiHasar) {
                            gameProperties.getBot().setSkor(5);
                            RaundStatus.setText("Son Raundu Kazanan: " + gameProperties.getBot().getOyuncuAdi());
                            showMessageDialog(null, "Raundu Kazanan: " + gameProperties.getBot().getOyuncuAdi());
                        } else if (kartBirHasar == kartIkiHasar) {
                            RaundStatus.setText("Son Raund Berabere");
                            showMessageDialog(null, "Raundu BERABERE");
                        }
                        //gameProperties.getBot().listedenKartCikart(kartNo);
                        raundScoreBoard();
                        DestedenCek();
                        destedenCekmeSayisi += 1;
                    }
                };
                int sure = 1000;
                saat.schedule(gorev, sure);

            }

        }
    }

    public void DestedenCek() {

        if (gameProperties.getGameType() == 0) {
            //BOT VS BOT
            if (destedenCekmeSayisi == 0) {
                gameProperties.getComputer().listeyeKartEkle(gameProperties.desk.get(0));
                gameProperties.getBot().listeyeKartEkle(gameProperties.desk.get(1));
                masa1.setVisible(false);
                masa2.setVisible(false);
                player4.setVisible(true);
                bilgisayar4.setVisible(true);

                player4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(3).isImage())));
                bilgisayar4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(3).isImage())));
                kartlariDagit();
            } else if (destedenCekmeSayisi == 1) {
                gameProperties.getComputer().listeyeKartEkle(gameProperties.desk.get(2));
                gameProperties.getBot().listeyeKartEkle(gameProperties.desk.get(3));
                masa3.setVisible(false);
                masa4.setVisible(false);
                player5.setVisible(true);
                bilgisayar5.setVisible(true);

                player5.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(4).isImage())));
                bilgisayar5.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(4).isImage())));
                kartlariDagit();
            }

        } else if (gameProperties.getGameType() == 1) {
            // INSAN VS BOT
            if (destedenCekmeSayisi == 0) {
                gameProperties.getPlayer().listeyeKartEkle(gameProperties.desk.get(0));
                gameProperties.getBot().listeyeKartEkle(gameProperties.desk.get(1));
                masa1.setVisible(false);
                masa2.setVisible(false);
                player4.setVisible(true);
                bilgisayar4.setVisible(true);

                player4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getPlayer().kartListesi().get(3).isImage())));
                bilgisayar4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(3).isImage())));
                kartlariDagit();
            } else if (destedenCekmeSayisi == 1) {
                gameProperties.getPlayer().listeyeKartEkle(gameProperties.desk.get(2));
                gameProperties.getBot().listeyeKartEkle(gameProperties.desk.get(3));
                masa3.setVisible(false);
                masa4.setVisible(false);
                player5.setVisible(true);
                bilgisayar5.setVisible(true);

                player5.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getPlayer().kartListesi().get(4).isImage())));
                bilgisayar5.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(4).isImage())));
                kartlariDagit();
            }
        }
        if (destedenCekmeSayisi == 4) {
            oyunBitir();
        }
    }

    public void oyunBitir() {
        botNextRaundBtn.setEnabled(false);
        String messageText = "";
        messageText += "Oyun Bitti\n";
        messageText += gameProperties.getBot().getOyuncuAdi() + ": Puan:" + gameProperties.getBot().getSkor() + "\n";
        if (gameProperties.getGameType() == 1) {

            messageText += gameProperties.getPlayer().getOyuncuAdi() + ": Puan:" + gameProperties.getPlayer().getSkor() + "\n";

            if (gameProperties.getBot().getSkor() > gameProperties.getPlayer().getSkor()) {
                messageText += "Kazanan: " + gameProperties.getBot().getOyuncuAdi();
            } else if (gameProperties.getBot().getSkor() < gameProperties.getPlayer().getSkor()) {
                messageText += "Kazanan: " + gameProperties.getPlayer().getOyuncuAdi();
            } else if (gameProperties.getBot().getSkor() == gameProperties.getPlayer().getSkor()) {
                messageText += "BERABERE";
            }

        } else if (gameProperties.getGameType() == 0) {
            messageText += gameProperties.getComputer().getOyuncuAdi() + ": Puan:" + gameProperties.getComputer().getSkor() + "\n";
            if (gameProperties.getBot().getSkor() > gameProperties.getComputer().getSkor()) {
                messageText += "Kazanan: " + gameProperties.getBot().getOyuncuAdi();
            } else if (gameProperties.getBot().getSkor() < gameProperties.getComputer().getSkor()) {
                messageText += "Kazanan: " + gameProperties.getComputer().getOyuncuAdi();
            } else if (gameProperties.getBot().getSkor() == gameProperties.getComputer().getSkor()) {
                messageText += "BERABERE";
            }

        }

        System.out.println("-------------OYUN BITTI-------------");

        showMessageDialog(null, messageText);
        showMessageDialog(null, "Oyun bitti.\nTekrar oynamak için lütfen <[Yeniden Oyna]> tuşuna basınız...");
        RaundStatus.setText("");
        messageText = "";

    }

    public void kartlariDagit() {

        if (gameProperties.getGameType() == 0) {

            player1.setBounds(260, 450, 120, 200);
            player2.setBounds(390, 450, 120, 200);
            player3.setBounds(520, 450, 120, 200);
            player4.setBounds(650, 450, 120, 200);
            player5.setBounds(780, 450, 120, 200);

        }
        if (debug == true) {
            bilgisayar1.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(0).isImage()))); // NOI18N
            bilgisayar2.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(1).isImage()))); // NOI18N
            bilgisayar3.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(2).isImage()))); // NOI18N
            if (destedenCekmeSayisi > 0) {
                bilgisayar4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(3).isImage()))); // NOI18N
            }
            if (destedenCekmeSayisi > 1) {
                bilgisayar5.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getBot().kartListesi().get(4).isImage()))); // NOI18N
            }
            masa1.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.desk.get(0).isImage())));
            masa2.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.desk.get(1).isImage())));
            masa3.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.desk.get(2).isImage())));
            masa4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.desk.get(3).isImage())));

            if (gameProperties.getGameType() == 0) {
                player1.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(0).isImage()))); // NOI18N
                player2.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(1).isImage()))); // NOI18N
                player3.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(2).isImage()))); // NOI18N
                if (destedenCekmeSayisi == 1) {
                    player4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(3).isImage()))); // NOI18N
                }
                if (destedenCekmeSayisi > 0) {
                    player4.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(3).isImage()))); // NOI18N
                }
                if (destedenCekmeSayisi > 1) {
                    player5.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getComputer().kartListesi().get(4).isImage()))); // NOI18N
                }
            }
        } else if (debug == false) {

            bilgisayar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
            bilgisayar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
            bilgisayar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
            bilgisayar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
            bilgisayar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N

            masa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
            masa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
            masa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
            masa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));

            if (gameProperties.getGameType() == 0) {
                player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
                player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
                player3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
                player4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N
                player5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png"))); // NOI18N

            }
        }

        if (gameProperties.getGameType() == 1) {
            player1.setBounds(260, 500, 120, 200);
            player2.setBounds(390, 500, 120, 200);
            player3.setBounds(520, 500, 120, 200);
            player4.setBounds(650, 500, 120, 200);
            player5.setBounds(780, 500, 120, 200);
            player1.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getPlayer().kartListesi().get(0).isImage()))); // NOI18N
            player2.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getPlayer().kartListesi().get(1).isImage()))); // NOI18N
            player3.setIcon(new javax.swing.ImageIcon(getClass().getResource(gameProperties.getPlayer().kartListesi().get(2).isImage()))); // NOI18N

        }
    }

    public void isDebug() {
        debug = !debug;
        kartlariDagit();
    }

    public void restart() {
        gameProperties.restart();
        secilen.removeAll(secilen);
        gameProperties.getComputer().kartListesi().removeAll(gameProperties.getComputer().kartListesi());
        gameProperties.getPlayer().kartListesi().removeAll(gameProperties.getPlayer().kartListesi());
        gameProperties.getBot().kartListesi().removeAll(gameProperties.getBot().kartListesi());
        this.debug = false;
        this.sira = 0;
        this.kartBirHasar = 0;
        this.kartIkiHasar = 0;
        this.destedenCekmeSayisi = 0;
        botNextRaundBtn.setEnabled(true);
        player1.setVisible(true);
        player2.setVisible(true);
        player3.setVisible(true);
        bilgisayar1.setVisible(true);
        bilgisayar2.setVisible(true);
        bilgisayar3.setVisible(true);
        masa1.setVisible(true);
        masa2.setVisible(true);
        masa3.setVisible(true);
        masa4.setVisible(true);
        gameProperties.getComputer().resetSkor();
        gameProperties.getBot().resetSkor();
        gameProperties.getPlayer().resetSkor();

        RaundStatus.setText("");
        gameBoard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
        gameBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards/Cardback.png")));
        DebugBtn.setBackground(new java.awt.Color(220, 53, 69));
        DebugBtn.setText("Debug [OFF]");
        jPanel1.setVisible(true);
        jPanel2.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bilgisayar1 = new javax.swing.JButton();
        bilgisayar2 = new javax.swing.JButton();
        bilgisayar3 = new javax.swing.JButton();
        bilgisayar4 = new javax.swing.JButton();
        bilgisayar5 = new javax.swing.JButton();
        debugCardList = new javax.swing.JButton();
        masa1 = new javax.swing.JButton();
        masa2 = new javax.swing.JButton();
        masa3 = new javax.swing.JButton();
        masa4 = new javax.swing.JButton();
        player1 = new javax.swing.JButton();
        player2 = new javax.swing.JButton();
        player3 = new javax.swing.JButton();
        player4 = new javax.swing.JButton();
        player5 = new javax.swing.JButton();
        panel2Gizle = new javax.swing.JButton();
        playerOneName = new javax.swing.JLabel();
        playerTwoName = new javax.swing.JLabel();
        playerTwoScore = new javax.swing.JLabel();
        playerOneScore = new javax.swing.JLabel();
        gameBoard1 = new javax.swing.JLabel();
        gameBoard2 = new javax.swing.JLabel();
        DebugBtn = new javax.swing.JButton();
        RaundStatus = new javax.swing.JLabel();
        botNextRaundBtn = new javax.swing.JButton();
        backGround = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PCtoPC = new javax.swing.JButton();
        PlayerToPc = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pokemon Kagit Savaslari");
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel2.setLayout(null);

        bilgisayar1.setBackground(new java.awt.Color(255, 255, 255));
        bilgisayar1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bilgisayar1.setActionCommand("bilgisayar1");
        bilgisayar1.setAlignmentY(0.0F);
        bilgisayar1.setBorder(null);
        bilgisayar1.setBorderPainted(false);
        bilgisayar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilgisayar1.setMaximumSize(new java.awt.Dimension(100, 42));
        bilgisayar1.setMinimumSize(new java.awt.Dimension(100, 42));
        bilgisayar1.setOpaque(false);
        bilgisayar1.setPreferredSize(new java.awt.Dimension(100, 40));
        bilgisayar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgisayar1ActionPerformed(evt);
            }
        });
        jPanel2.add(bilgisayar1);
        bilgisayar1.setBounds(260, 0, 120, 200);

        bilgisayar2.setBackground(new java.awt.Color(255, 255, 255));
        bilgisayar2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bilgisayar2.setActionCommand("bilgisayar2");
        bilgisayar2.setAlignmentY(0.0F);
        bilgisayar2.setBorder(null);
        bilgisayar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilgisayar2.setMaximumSize(new java.awt.Dimension(100, 40));
        bilgisayar2.setMinimumSize(new java.awt.Dimension(100, 40));
        bilgisayar2.setOpaque(false);
        bilgisayar2.setPreferredSize(new java.awt.Dimension(100, 40));
        bilgisayar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgisayar2ActionPerformed(evt);
            }
        });
        jPanel2.add(bilgisayar2);
        bilgisayar2.setBounds(390, 0, 120, 200);

        bilgisayar3.setBackground(new java.awt.Color(255, 255, 255));
        bilgisayar3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bilgisayar3.setActionCommand("bilgisayar3");
        bilgisayar3.setAlignmentY(0.0F);
        bilgisayar3.setBorder(null);
        bilgisayar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bilgisayar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilgisayar3.setMaximumSize(new java.awt.Dimension(100, 40));
        bilgisayar3.setMinimumSize(new java.awt.Dimension(100, 40));
        bilgisayar3.setOpaque(false);
        bilgisayar3.setPreferredSize(new java.awt.Dimension(100, 40));
        bilgisayar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgisayar3ActionPerformed(evt);
            }
        });
        jPanel2.add(bilgisayar3);
        bilgisayar3.setBounds(520, 0, 120, 200);

        bilgisayar4.setBackground(new java.awt.Color(255, 255, 255));
        bilgisayar4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bilgisayar4.setActionCommand("bilgisayar1");
        bilgisayar4.setAlignmentY(0.0F);
        bilgisayar4.setBorder(null);
        bilgisayar4.setBorderPainted(false);
        bilgisayar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilgisayar4.setMaximumSize(new java.awt.Dimension(100, 42));
        bilgisayar4.setMinimumSize(new java.awt.Dimension(100, 42));
        bilgisayar4.setOpaque(false);
        bilgisayar4.setPreferredSize(new java.awt.Dimension(100, 40));
        bilgisayar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgisayar4ActionPerformed(evt);
            }
        });
        jPanel2.add(bilgisayar4);
        bilgisayar4.setBounds(650, 0, 120, 200);

        bilgisayar5.setBackground(new java.awt.Color(255, 255, 255));
        bilgisayar5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bilgisayar5.setActionCommand("bilgisayar1");
        bilgisayar5.setAlignmentY(0.0F);
        bilgisayar5.setBorder(null);
        bilgisayar5.setBorderPainted(false);
        bilgisayar5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilgisayar5.setMaximumSize(new java.awt.Dimension(100, 42));
        bilgisayar5.setMinimumSize(new java.awt.Dimension(100, 42));
        bilgisayar5.setOpaque(false);
        bilgisayar5.setPreferredSize(new java.awt.Dimension(100, 40));
        bilgisayar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgisayar5ActionPerformed(evt);
            }
        });
        jPanel2.add(bilgisayar5);
        bilgisayar5.setBounds(780, 0, 125, 200);

        debugCardList.setBorder(null);
        debugCardList.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                debugCardListMouseMoved(evt);
            }
        });
        debugCardList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                debugCardListMouseExited(evt);
            }
        });
        debugCardList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugCardListActionPerformed(evt);
            }
        });
        jPanel2.add(debugCardList);
        debugCardList.setBounds(10, 210, 150, 230);

        masa1.setBackground(new java.awt.Color(255, 255, 255));
        masa1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        masa1.setActionCommand("masa1");
        masa1.setAlignmentY(0.0F);
        masa1.setBorder(null);
        masa1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        masa1.setHideActionText(true);
        masa1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        masa1.setMaximumSize(new java.awt.Dimension(100, 40));
        masa1.setMinimumSize(new java.awt.Dimension(100, 40));
        masa1.setPreferredSize(new java.awt.Dimension(100, 40));
        masa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masa1ActionPerformed(evt);
            }
        });
        jPanel2.add(masa1);
        masa1.setBounds(40, 210, 120, 200);

        masa2.setBackground(new java.awt.Color(255, 255, 255));
        masa2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        masa2.setActionCommand("masa2");
        masa2.setBorder(null);
        masa2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        masa2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        masa2.setMaximumSize(new java.awt.Dimension(100, 40));
        masa2.setMinimumSize(new java.awt.Dimension(100, 40));
        masa2.setPreferredSize(new java.awt.Dimension(100, 40));
        masa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masa2ActionPerformed(evt);
            }
        });
        jPanel2.add(masa2);
        masa2.setBounds(30, 220, 120, 200);

        masa3.setBackground(new java.awt.Color(255, 255, 255));
        masa3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        masa3.setActionCommand("masa3");
        masa3.setAlignmentY(0.0F);
        masa3.setBorder(null);
        masa3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        masa3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        masa3.setMaximumSize(new java.awt.Dimension(100, 40));
        masa3.setMinimumSize(new java.awt.Dimension(100, 40));
        masa3.setPreferredSize(new java.awt.Dimension(100, 40));
        masa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masa3ActionPerformed(evt);
            }
        });
        jPanel2.add(masa3);
        masa3.setBounds(20, 230, 120, 200);

        masa4.setBackground(new java.awt.Color(255, 255, 255));
        masa4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        masa4.setActionCommand("masa4");
        masa4.setBorder(null);
        masa4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        masa4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        masa4.setMaximumSize(new java.awt.Dimension(100, 40));
        masa4.setMinimumSize(new java.awt.Dimension(100, 40));
        masa4.setPreferredSize(new java.awt.Dimension(100, 40));
        masa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masa4ActionPerformed(evt);
            }
        });
        jPanel2.add(masa4);
        masa4.setBounds(10, 240, 120, 200);

        player1.setBackground(new java.awt.Color(255, 255, 255));
        player1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        player1.setActionCommand("player1");
        player1.setAlignmentY(0.0F);
        player1.setBorder(null);
        player1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        player1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        player1.setMaximumSize(new java.awt.Dimension(100, 40));
        player1.setMinimumSize(new java.awt.Dimension(100, 40));
        player1.setOpaque(false);
        player1.setPreferredSize(new java.awt.Dimension(100, 40));
        player1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                player1MouseMoved(evt);
            }
        });
        player1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                player1MouseExited(evt);
            }
        });
        player1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player1ActionPerformed(evt);
            }
        });
        jPanel2.add(player1);
        player1.setBounds(260, 500, 120, 200);

        player2.setBackground(new java.awt.Color(255, 255, 255));
        player2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        player2.setActionCommand("player2");
        player2.setAlignmentY(0.0F);
        player2.setBorder(null);
        player2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        player2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        player2.setMaximumSize(new java.awt.Dimension(100, 40));
        player2.setMinimumSize(new java.awt.Dimension(100, 40));
        player2.setOpaque(false);
        player2.setPreferredSize(new java.awt.Dimension(100, 40));
        player2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                player2MouseMoved(evt);
            }
        });
        player2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                player2MouseExited(evt);
            }
        });
        player2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2ActionPerformed(evt);
            }
        });
        jPanel2.add(player2);
        player2.setBounds(390, 500, 120, 200);

        player3.setBackground(new java.awt.Color(255, 255, 255));
        player3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        player3.setActionCommand("player3");
        player3.setAlignmentY(0.0F);
        player3.setBorder(null);
        player3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        player3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        player3.setMaximumSize(new java.awt.Dimension(100, 40));
        player3.setMinimumSize(new java.awt.Dimension(100, 40));
        player3.setOpaque(false);
        player3.setPreferredSize(new java.awt.Dimension(100, 40));
        player3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                player3MouseMoved(evt);
            }
        });
        player3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                player3MouseExited(evt);
            }
        });
        player3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player3ActionPerformed(evt);
            }
        });
        jPanel2.add(player3);
        player3.setBounds(520, 500, 120, 200);

        player4.setBackground(new java.awt.Color(255, 255, 255));
        player4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        player4.setActionCommand("player1");
        player4.setAlignmentY(0.0F);
        player4.setBorder(null);
        player4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        player4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        player4.setMaximumSize(new java.awt.Dimension(100, 40));
        player4.setMinimumSize(new java.awt.Dimension(100, 40));
        player4.setOpaque(false);
        player4.setPreferredSize(new java.awt.Dimension(100, 40));
        player4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                player4MouseMoved(evt);
            }
        });
        player4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                player4MouseExited(evt);
            }
        });
        player4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player4ActionPerformed(evt);
            }
        });
        jPanel2.add(player4);
        player4.setBounds(650, 500, 120, 200);

        player5.setBackground(new java.awt.Color(255, 255, 255));
        player5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        player5.setActionCommand("player3");
        player5.setAlignmentY(0.0F);
        player5.setBorder(null);
        player5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        player5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        player5.setMaximumSize(new java.awt.Dimension(100, 40));
        player5.setMinimumSize(new java.awt.Dimension(100, 40));
        player5.setOpaque(false);
        player5.setPreferredSize(new java.awt.Dimension(100, 40));
        player5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                player5MouseMoved(evt);
            }
        });
        player5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                player5MouseExited(evt);
            }
        });
        player5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player5ActionPerformed(evt);
            }
        });
        jPanel2.add(player5);
        player5.setBounds(780, 500, 120, 200);

        panel2Gizle.setText("Yeniden Oyna");
        panel2Gizle.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 51, 0)));
        panel2Gizle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel2GizleActionPerformed(evt);
            }
        });
        jPanel2.add(panel2Gizle);
        panel2Gizle.setBounds(40, 610, 130, 40);

        playerOneName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        playerOneName.setForeground(new java.awt.Color(255, 255, 255));
        playerOneName.setText("Oyuncu");
        jPanel2.add(playerOneName);
        playerOneName.setBounds(20, 60, 170, 30);

        playerTwoName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        playerTwoName.setForeground(new java.awt.Color(255, 255, 255));
        playerTwoName.setText("Oyuncu");
        jPanel2.add(playerTwoName);
        playerTwoName.setBounds(20, 20, 170, 30);

        playerTwoScore.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        playerTwoScore.setText("0");
        jPanel2.add(playerTwoScore);
        playerTwoScore.setBounds(180, 30, 100, 20);

        playerOneScore.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        playerOneScore.setText("0");
        jPanel2.add(playerOneScore);
        playerOneScore.setBounds(180, 60, 80, 40);
        jPanel2.add(gameBoard1);
        gameBoard1.setBounds(1010, 80, 120, 200);
        jPanel2.add(gameBoard2);
        gameBoard2.setBounds(1010, 290, 120, 200);

        DebugBtn.setBackground(new java.awt.Color(220, 53, 69));
        DebugBtn.setText("Debug [OFF]");
        DebugBtn.setBorder(null);
        DebugBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebugBtnActionPerformed(evt);
            }
        });
        jPanel2.add(DebugBtn);
        DebugBtn.setBounds(40, 560, 130, 40);

        RaundStatus.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));
        RaundStatus.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        RaundStatus.setForeground(new java.awt.Color(255, 0, 0));
        RaundStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RaundStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 51), 2, true));
        RaundStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(RaundStatus);
        RaundStatus.setBounds(200, 270, 730, 130);

        botNextRaundBtn.setBackground(new java.awt.Color(153, 255, 51));
        botNextRaundBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botNextRaundBtn.setForeground(new java.awt.Color(255, 0, 51));
        botNextRaundBtn.setText("Yeni Raund");
        botNextRaundBtn.setBorder(null);
        botNextRaundBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNextRaundBtnActionPerformed(evt);
            }
        });
        jPanel2.add(botNextRaundBtn);
        botNextRaundBtn.setBounds(990, 500, 160, 50);

        backGround.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background.png"))); // NOI18N
        jPanel2.add(backGround);
        backGround.setBounds(0, 0, 1200, 700);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1200, 700);

        jPanel3.setLayout(null);

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Giris Yap");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(10, 130, 390, 110);

        jTextField1.setBackground(new java.awt.Color(51, 51, 255));
        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Oyun Adınız....");
        jPanel3.add(jTextField1);
        jTextField1.setBounds(10, 10, 390, 110);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pokemon-detective-pikachu-5k-en-5120x2880.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(0, 0, 1200, 700);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 1200, 700);

        jPanel1.setLayout(null);

        PCtoPC.setBackground(new java.awt.Color(255, 255, 255));
        PCtoPC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PCtoPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttons/Computer.png"))); // NOI18N
        PCtoPC.setAlignmentY(0.0F);
        PCtoPC.setBorder(null);
        PCtoPC.setContentAreaFilled(false);
        PCtoPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PCtoPCActionPerformed(evt);
            }
        });
        jPanel1.add(PCtoPC);
        PCtoPC.setBounds(10, 380, 320, 105);

        PlayerToPc.setBackground(new java.awt.Color(255, 255, 255));
        PlayerToPc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PlayerToPc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buttons/PlayerButton.png"))); // NOI18N
        PlayerToPc.setAlignmentY(0.0F);
        PlayerToPc.setBorder(null);
        PlayerToPc.setContentAreaFilled(false);
        PlayerToPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerToPcActionPerformed(evt);
            }
        });
        jPanel1.add(PlayerToPc);
        PlayerToPc.setBounds(10, 490, 320, 105);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pokemon-detective-pikachu-5k-en-5120x2880.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1200, 700);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1200, 700);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public Game(JButton DebugBtn, JButton PCtoPC, JButton PlayerToPc, JLabel RaundStatus, JLabel backGround, JButton bilgisayar1, JButton bilgisayar2, JButton bilgisayar3, JButton bilgisayar4, JButton bilgisayar5, JButton botNextRaundBtn, JButton debugCardList, JLabel gameBoard1, JLabel gameBoard2, JButton jButton1, JLabel jLabel1, JLabel jLabel3, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JTextField jTextField1, JButton masa1, JButton masa2, JButton masa3, JButton masa4, JButton panel2Gizle, JButton player1, JButton player2, JButton player3, JButton player4, JButton player5, JLabel playerOneName, JLabel playerOneScore, JLabel playerTwoName, JLabel playerTwoScore) {
        this.DebugBtn = DebugBtn;
        this.PCtoPC = PCtoPC;
        this.PlayerToPc = PlayerToPc;
        this.RaundStatus = RaundStatus;
        this.backGround = backGround;
        this.bilgisayar1 = bilgisayar1;
        this.bilgisayar2 = bilgisayar2;
        this.bilgisayar3 = bilgisayar3;
        this.bilgisayar4 = bilgisayar4;
        this.bilgisayar5 = bilgisayar5;
        this.botNextRaundBtn = botNextRaundBtn;
        this.debugCardList = debugCardList;
        this.gameBoard1 = gameBoard1;
        this.gameBoard2 = gameBoard2;
        this.jButton1 = jButton1;
        this.jLabel1 = jLabel1;
        this.jLabel3 = jLabel3;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jPanel3 = jPanel3;
        this.jTextField1 = jTextField1;
        this.masa1 = masa1;
        this.masa2 = masa2;
        this.masa3 = masa3;
        this.masa4 = masa4;
        this.panel2Gizle = panel2Gizle;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
        this.playerOneName = playerOneName;
        this.playerOneScore = playerOneScore;
        this.playerTwoName = playerTwoName;
        this.playerTwoScore = playerTwoScore;
    }

    private void PCtoPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PCtoPCActionPerformed
        // TODO add your handling code here:

        gameProperties.setGameType(0);
        ScoreBoard();
        kartlariDagit();
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_PCtoPCActionPerformed

    private void PlayerToPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerToPcActionPerformed
        // TODO add your handling code here:
        gameProperties.setGameType(1);
        jPanel3.setVisible(true);

        jPanel1.setVisible(false);

    }//GEN-LAST:event_PlayerToPcActionPerformed

    private void bilgisayar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgisayar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilgisayar2ActionPerformed

    private void bilgisayar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgisayar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilgisayar3ActionPerformed

    private void masa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_masa1ActionPerformed

    private void masa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_masa2ActionPerformed

    private void masa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_masa3ActionPerformed

    private void masa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masa4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_masa4ActionPerformed

    private void player1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1MouseMoved
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 500; i > 450; i--) {
                player1.setBounds(260, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player1MouseMoved

    private void player1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1MouseExited
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 450; i < 500; i++) {
                player1.setBounds(260, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player1MouseExited

    private void player1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player1ActionPerformed
        //buttonOne(create.playerTwoList.get(0));

        if (gameProperties.getGameType() == 1) {
            if (sira == 0) {
                oyun(gameProperties.getPlayer().kartListesi().get(0));
                player1.setVisible(false);

            }
        }
    }//GEN-LAST:event_player1ActionPerformed

    private void player2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2MouseMoved
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 500; i > 450; i--) {
                player2.setBounds(390, i, 120, 200);
            }
        }

    }//GEN-LAST:event_player2MouseMoved

    private void player2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2MouseExited
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {

            for (int i = 450; i < 500; i++) {
                player2.setBounds(390, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player2MouseExited

    private void player2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2ActionPerformed
        // TODO add your handling code here:
        //buttonTwo(create.playerTwoList.get(1));
        if (gameProperties.getGameType() == 1) {
            if (sira == 0) {
                oyun(gameProperties.getPlayer().kartListesi().get(1));
                player2.setVisible(false);

            }
        }
    }//GEN-LAST:event_player2ActionPerformed

    private void player3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player3MouseMoved
        // TODO add your handling code here:

        if (gameProperties.getGameType() == 1) {
            for (int i = 500; i > 450; i--) {
                player3.setBounds(520, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player3MouseMoved

    private void player3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player3MouseExited
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 450; i < 500; i++) {
                player3.setBounds(520, i, 120, 200);
            }
        }

    }//GEN-LAST:event_player3MouseExited

    private void player3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player3ActionPerformed
        // TODO add your handling code here:
        //buttonTree(create.playerTwoList.get(2));
        if (gameProperties.getGameType() == 1) {
            if (sira == 0) {
                oyun(gameProperties.getPlayer().kartListesi().get(2));
                player3.setVisible(false);

            }
        }
    }//GEN-LAST:event_player3ActionPerformed

    private void bilgisayar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgisayar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilgisayar1ActionPerformed


    private void panel2GizleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panel2GizleActionPerformed

        restart();


    }//GEN-LAST:event_panel2GizleActionPerformed

    private void DebugBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebugBtnActionPerformed
        // TODO add your handling code here:
        if (debug != true) {
            DebugBtn.setText("Debug [ON]");
            DebugBtn.setBackground(new java.awt.Color(40, 167, 69));
        }
        if (debug != false) {
            DebugBtn.setText("Debug [OFF]");
            DebugBtn.setBackground(new java.awt.Color(220, 53, 69));

        }
        isDebug();
    }//GEN-LAST:event_DebugBtnActionPerformed

    private void debugCardListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugCardListActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_debugCardListActionPerformed

    private void debugCardListMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debugCardListMouseMoved
        // TODO add your handling code here:
        if (debug == true) {
            masa1.setBounds(30, 230, 120, 200);
            masa2.setBounds(150, 230, 120, 200);
            masa3.setBounds(270, 230, 120, 200);
            masa4.setBounds(390, 230, 120, 200);

        }
    }//GEN-LAST:event_debugCardListMouseMoved

    private void debugCardListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debugCardListMouseExited
        // TODO add your handling code here:
        if (debug == true) {
            masa1.setBounds(30, 200, 120, 200);
            masa2.setBounds(20, 210, 120, 200);
            masa3.setBounds(10, 220, 120, 200);
            masa4.setBounds(0, 230, 120, 200);
        }
    }//GEN-LAST:event_debugCardListMouseExited

    private void player4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player4MouseMoved
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 500; i > 450; i--) {
                player4.setBounds(650, i, 120, 200);
            }
        }

    }//GEN-LAST:event_player4MouseMoved

    private void player4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player4MouseExited
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 450; i < 500; i++) {
                player4.setBounds(650, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player4MouseExited

    private void player4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player4ActionPerformed
        // TODO add your handling code here:
        //120
        if (gameProperties.getGameType() == 1) {
            if (sira == 0) {
                oyun(gameProperties.getPlayer().kartListesi().get(3));
                player4.setVisible(false);

            }
        }
    }//GEN-LAST:event_player4ActionPerformed

    private void bilgisayar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgisayar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilgisayar4ActionPerformed

    private void bilgisayar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgisayar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilgisayar5ActionPerformed

    private void player5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player5MouseMoved
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 500; i > 450; i--) {
                player5.setBounds(780, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player5MouseMoved

    private void player5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player5MouseExited
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            for (int i = 450; i < 500; i++) {
                player5.setBounds(780, i, 120, 200);
            }
        }
    }//GEN-LAST:event_player5MouseExited

    private void player5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player5ActionPerformed
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 1) {
            if (sira == 0) {
                oyun(gameProperties.getPlayer().kartListesi().get(4));
                player5.setVisible(false);

            }
        }
    }//GEN-LAST:event_player5ActionPerformed

    private void botNextRaundBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNextRaundBtnActionPerformed
        // TODO add your handling code here:
        if (gameProperties.getGameType() == 0) {
            if (sira == 0) {
                oyun(gameProperties.getComputer().kartSec());
            }
        }
    }//GEN-LAST:event_botNextRaundBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        gameProperties.getPlayer().setOyuncuAdi(jTextField1.getText());

        ScoreBoard();
        kartlariDagit();
        jPanel3.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DebugBtn;
    private javax.swing.JButton PCtoPC;
    private javax.swing.JButton PlayerToPc;
    private javax.swing.JLabel RaundStatus;
    private javax.swing.JLabel backGround;
    private javax.swing.JButton bilgisayar1;
    private javax.swing.JButton bilgisayar2;
    private javax.swing.JButton bilgisayar3;
    private javax.swing.JButton bilgisayar4;
    private javax.swing.JButton bilgisayar5;
    private javax.swing.JButton botNextRaundBtn;
    private javax.swing.JButton debugCardList;
    private javax.swing.JLabel gameBoard1;
    private javax.swing.JLabel gameBoard2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton masa1;
    private javax.swing.JButton masa2;
    private javax.swing.JButton masa3;
    private javax.swing.JButton masa4;
    private javax.swing.JButton panel2Gizle;
    private javax.swing.JButton player1;
    private javax.swing.JButton player2;
    private javax.swing.JButton player3;
    private javax.swing.JButton player4;
    private javax.swing.JButton player5;
    private javax.swing.JLabel playerOneName;
    private javax.swing.JLabel playerOneScore;
    private javax.swing.JLabel playerTwoName;
    private javax.swing.JLabel playerTwoScore;
    // End of variables declaration//GEN-END:variables
}
