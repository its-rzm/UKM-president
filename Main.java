//FINAL PROJECT ITP
/*UKM President E-Voting System*/
/*com.Final Project.Main*/

import java.util.Scanner;
import java.util.Vector;

public class Main {

	//GLOBAL VARIABLE
	Scanner input = new Scanner(System.in);
	String usernameGlobal;

	/*VECTOR OBJECT BASE*/
	Vector<Integer> vVote = new Vector<>();
	Vector<Integer> vSuara = new Vector<>();
	Vector<Integer> vNo = new Vector<>();
	Vector<String> vName = new Vector<>();
	Vector<String> vPass = new Vector<>();
	Vector<String> vKetum = new Vector<>();
	Vector<String> vVisi = new Vector<>();
	Vector<String> vMisi = new Vector<>();

	/*ADMIN CREDENTIAL*/
	String adminUser="admin", adminPass="123";

	/*DEFAULT USER*/
	private void defaultUser(){
		vName.add("defuser10");
		vPass.add("123");
	}

	/*CLEAR SCREEN*/
	private void hapusScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				return;
			} else {
				System.out.print("\033\143");
			}
		} catch (Exception ex) {
			for (int i = 0; i < 49; i++) {
				System.out.println("\n");
			}
		}
	}

	private void titleMain() {
		System.out.println("     UKM President E-Voting System     ");
		System.out.println("+=====================================+");
		System.out.println("1. Mahasiswa Baru (Register)");
		System.out.println("2. Vote Sekarang (Login)");
		System.out.println("3. Admin Panel");
		System.out.println("4. Exit App");
		System.out.println("---------------------------------------");
	}

	void swap(Vector<Integer> vSwap, int i, int j) {
		int temp = vSwap.get(i);
		vSwap.set(i, vSwap.get(j));
		vSwap.set(j, temp);
	}

	public void databaseKandidat() {
		for (int i = 0; i < vKetum.size(); i++) {
			System.out.println("|NOMOR KANDIDAT  | : "+(i+1));
			System.out.println("|NAMA KETUA UMUM | : "+vKetum.get(i));
			System.out.println("|VISI KANDIDAT   | : "+vVisi.get(i));
			System.out.println("|MISI KANDIDAT   | : "+vMisi.get(i));
			System.out.println();
		}
	}

	public void userPanel() {
		String userChoose = "", konfirmasi;
		int pilihKandidat = 0, limitPilih = 0, salt = vName.indexOf(usernameGlobal);
		do {
			hapusScreen();
			System.out.println("                -HALAMAN USER-               ");
			System.out.println("+===========================================+");
			System.out.println("SELAMAT DATANG : " + vName.get(salt));
			System.out.println("1. Lihat Visi Misi Kandidat");
			System.out.println("2. Vote Pilihanmu");
			System.out.println("3. Log Out");
			System.out.print("Pilih >> "); userChoose = input.nextLine();
			switch (userChoose) {
			case "1":
				System.out.println("              -Visi Misi Kandidat-           ");
				System.out.println("+===========================================+");
				if (vKetum.isEmpty()) {
					System.out.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					databaseKandidat();
					input.nextLine();
				}
				hapusScreen();
				break;
			case "2":
				System.out.println("               -Voting Pilihan-             ");
				System.out.println("+===========================================+");
				if (limitPilih == 1) {
					System.out.println("ANDA SUDAH MEMILIH!");
					input.nextLine();
					break;
				}
				if (vKetum.isEmpty()) {
					System.out.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					for (int i = 0; i < vKetum.size(); i++) {
						System.out.println("|NOMOR KANDIDAT  | : "+(i+1));
						System.out.println("|NAMA KETUA UMUM | : "+vKetum.get(i));
						System.out.println();
					}

					do {
						System.out.print("Pilih Kandidat Nomor : "); pilihKandidat = input.nextInt(); input.nextLine();
						if (!vNo.contains(pilihKandidat)) {
							System.out.println("Pilihan tidak tersedia");
						}
					} while (!vNo.contains(pilihKandidat));

					System.out.println("Anda Memilih Nomor "+pilihKandidat);
					System.out.print("Apakah Anda Yakin (Y/N) : "); konfirmasi = input.nextLine();
					if (!konfirmasi.equalsIgnoreCase("y")) {
						System.out.println("\nSilahkan Lihat Visi Misi Kembali!");
						input.nextLine();
					} else {
						vVote.add(pilihKandidat);
						limitPilih = 1;
						vName.set(salt, usernameGlobal+" SUDAH MEMILIH");
						System.out.println("\nPilihan Anda Disimpan!");
						input.nextLine();
					}
				}
				hapusScreen();
				break;
			case "3":
				System.err.println("\nLOG OUT DARI AKUN");
				input.nextLine();
				break;

			default:
				break;
			}
		} while (!(userChoose.equals("3")));

	}

	public void adminPanel() {
		String adminChoose = "";
		do {
			hapusScreen();
			System.out.println("                  -HALAMAN ADMIN-               ");
			System.out.println("+==============================================+");
			System.out.println("1. Tambahkan Kandidat         6. Lihat List User");
			System.out.println("2. Lihat List Kandidat        7. Cari User");
			System.out.println("3. Hapus Kandidat             8. Hapus User");
			System.out.println("4. Perbarui Kandidat          9. Perbarui User");
			System.out.println("5. Lihat Hasil Voting        10. LOG OUT");
			System.out.print("Pilih >> "); adminChoose = input.nextLine();
			switch (adminChoose) {
			case "1":
				int jumKandidat;
				String namaKetuaUmum = null, tambahVisi = null, tambahMisi = null;
				System.out.print("Tambah Jumlah Kandidat : "); jumKandidat = input.nextInt();
				input.nextLine();
				for (int i = 1; i <= jumKandidat; i++) {
					System.out.print("Nama Calon Ketua Umum "+i+" : "); namaKetuaUmum = input.nextLine();
					vKetum.add(namaKetuaUmum);
					vNo.add(i);
				}
				System.out.println();

				for (int i = 1; i <= jumKandidat; i++) {
					System.out.print("Visi Calon Ketua Umum "+i+" : "); tambahVisi = input.nextLine();
					vVisi.add(tambahVisi);
				}
				System.out.println();

				for (int i = 1; i <= jumKandidat; i++) {
					System.out.print("Misi Calon Ketua Umum "+i+" : "); tambahMisi = input.nextLine();
					vMisi.add(tambahMisi);
				}

				System.out.println("\nBERHASIL DITAMBAHKAN");
				input.nextLine();
				hapusScreen();
				break;
			case "2":
				System.out.println("\n    -DATABASE KANDIDAT-    ");
				System.out.println("=============================");
				if (vKetum.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					databaseKandidat();
					input.nextLine();
				}
				hapusScreen();
				break;
			case "3":
				int hapus;
				System.out.println("\n-HAPUS DATA KANDIDAT-");
				if (vKetum.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					databaseKandidat();
					System.out.print("Nomor Kandidat yang Ingin Dihapus: "); hapus = input.nextInt(); input.nextLine();
					hapus--;
					try {
						if(vNo.get(hapus) == hapus+1) {
							vKetum.remove(hapus);
							vVisi.remove(hapus);
							vMisi.remove(hapus);
							vNo.remove(hapus);
							System.out.println("\nBerhasil Hapus Kandidat Nomor " + (hapus+1));
							input.nextLine();
						}
					} catch(Exception E) {
						System.err.println("Tidak Menemukan Data");
						input.nextLine();
					}
				}
				hapusScreen();
				break;
			case "4":
				int update;
				String ketum, visi, misi;
				System.out.println("\n-PERBARUI DATA KANDIDAT-");
				if (vKetum.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					databaseKandidat();
					System.out.print("Nomor Kandidat yang Ingin Diperbarui: "); update = input.nextInt(); input.nextLine();
					update--;
					try {
						if(vNo.get(update) == update+1) {
							System.out.print("Nama Ketua Umum : "); ketum = input.nextLine();
							System.out.print("Visi Ketua Umum : "); visi = input.nextLine();
							System.out.print("Misi Ketua Umum : "); misi = input.nextLine();
							vKetum.set(update, ketum);
							vVisi.set(update, visi);
							vMisi.set(update, misi);
							System.out.println("\nBerhasil Perbarui Kandidat Nomor " + (update+1));
							input.nextLine();
						}
					} catch(Exception E) {
						System.err.println("Tidak Menemukan Data");
						input.nextLine();
					}
				}
				hapusScreen();

				break;

			case "5":
				System.out.println("\n      -DATABASE VOTING-      ");
				System.out.println("===============================");
				if (vVote.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					for (int i = 0; i < vNo.size(); i++) {
						int res = 0;
						System.out.println("KANDIDAT NOMOR " + vNo.get(i));
						for (int j = 0; j < vVote.size(); j++) {
							if (vNo.get(i) == vVote.get(j)) {
								res++;
							}
						}

						try {
							if (vSuara.get(i) > 0) {
								vSuara.set(i, res);
							}
						} catch (Exception e) {
							vSuara.add(res);
						}

						System.out.println("TOTAL SUARA TERKUMPUL : " + vSuara.get(i));
						System.out.println();
					}

					int pilih;
					do {
						System.out.println("1. Sort Jumlah Tertinggi - Terkecil");
						System.out.println("2. Sort Jumlah Terkecil - Tertinggi");
						System.out.println("3. Kembali");
						System.out.print("Pilih >> "); pilih = input.nextInt(); input.nextLine();

						if(pilih == 1) {
							for (int i = 0; i < vSuara.size(); i++) {
								for (int j = 0; j < vSuara.size()-i-1; j++) {
									if (vSuara.get(j).compareTo(vSuara.get(j+1)) < 0) {
										swap(vNo,j,j+1);
										swap(vSuara,j,j+1);
									}
								}
							}

							for (int i=0; i < vNo.size(); i++) {
								System.out.println("KANDIDAT NOMOR " + vNo.get(i));
								System.out.println("TOTAL SUARA TERKUMPUL : " + vSuara.get(i));
								System.out.println();
							}

						} else if (pilih == 2) {
							for (int i = 0; i < vSuara.size(); i++) {
								for (int j = 0; j < vSuara.size()-i-1; j++) {
									if (vSuara.get(j).compareTo(vSuara.get(j+1)) > 0) {
										swap(vNo,j,j+1);
										swap(vSuara,j,j+1);
									}
								}
							}

							for (int i=0; i < vNo.size(); i++) {
								System.out.println("KANDIDAT NOMOR " + vNo.get(i));
								System.out.println("TOTAL SUARA TERKUMPUL : " + vSuara.get(i));
								System.out.println();
							}
						}
					} while (pilih != 3);
				}
				hapusScreen();
				break;

			case "6":
				System.out.println("\n       -DATABASE USERS-       ");
				System.out.println("=========================================================");
				if (vName.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					System.out.println("NO.  | USERNAME\t\t\t\t | PASSWORD\t |");
					for (int i = 0; i < vName.size(); i++) {
						System.out.printf("%-4d | %-33s | %-13s |", (i+1), vName.get(i), vPass.get(i));
						System.out.println();
					}
					input.nextLine();
				}
				hapusScreen();
				break;

			case "7":
				String cari;
				System.out.println("\n-CARI DATA USER-");
				System.out.print("Masukan username yang ingin dicari : "); cari = input.nextLine();
				for (int i = 0; i < vName.size(); i++) {
					if (vName.contains(cari)) {
						int a = vName.indexOf(cari);
						System.out.println("\nUsername "+cari+" ditemukan\n");
						System.out.println("| USERNAME\t\t | PASSWORD\t |");
						System.out.printf("| %-22s | %-13s |", vName.get(a), vPass.get(a));
					}
				}
				if (!vName.contains(cari)) {
					System.out.println("Tidak menemukan username yang dicari");
				}
				input.nextLine();
				break;

			case "8":
				String hapusUser;
				System.out.println("\n-HAPUS DATA USER-");
				if (vName.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					System.out.println("NO.  | USERNAME\t\t\t\t | PASSWORD\t |");
					for (int i = 0; i < vName.size(); i++) {
						System.out.printf("%-4d | %-33s | %-13s |", (i+1), vName.get(i), vPass.get(i));
						System.out.println();
					}
					try {
						System.out.print("\nUser yang ingin dihapus: "); hapusUser = input.nextLine();
						if(vName.contains(hapusUser)) {
							int a = vName.indexOf(hapusUser);
							vName.remove(hapusUser);
							vPass.remove(a);
							vNo.remove(a);
							System.out.println("\nBerhasil Hapus User " + (hapusUser));
							input.nextLine();
						} else {
							System.err.println("Tidak Menemukan Data");
							input.nextLine();
						}
					} catch(Exception E) {
						System.err.println("Tidak Menemukan Data");
						input.nextLine();
					}
				}
				hapusScreen();
				break;

			case "9":
				String updateUser, updateUsername, updatePassword;
				System.out.println("\n-PERBARUI DATA USER-");
				if (vName.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					input.nextLine();
				} else {
					System.out.println("NO.  | USERNAME\t\t\t\t | PASSWORD\t |");
					for (int i = 0; i < vName.size(); i++) {
						System.out.printf("%-4d | %-33s | %-13s |", (i+1), vName.get(i), vPass.get(i));
						System.out.println();
					}
					try {
						System.out.print("\nUser yang ingin diperbarui: "); updateUser = input.nextLine();
						if(vName.contains(updateUser)) {
							int a = vName.indexOf(updateUser);
							System.out.print("Username : "); updateUsername = input.nextLine();
							System.out.print("Password : "); updatePassword = input.nextLine();
							vName.set(a, updateUsername);
							vPass.set(a, updatePassword);
							System.out.println("\nBerhasil Perbarui User " + (updateUser));
							input.nextLine();
						} else {
							System.err.println("\nTidak Menemukan user " + (updateUser));
							input.nextLine();
						}
					}
					catch(Exception E) {
						System.err.println("Tidak Menemukan Data");
						input.nextLine();
					}
				}
				hapusScreen();
				break;	

			case "10":

				System.err.println("\nLOG OUT DARI ADMIN");
				input.nextLine();
				break;

			default:
				break;
			}
		} while (!(adminChoose.equals("10")));
	}

	public void registerSystem() {
		System.out.println("              -ACCOUNT REGISTER-             ");
		System.out.println("+===========================================+");
		String isNumeric = ("-?\\d+(\\.\\d+)?"), verifPass, username, password;
		do {
			System.out.print("| STUDENT ID (10 Digits)  | : "); username = input.nextLine();
			if (!(username.matches(isNumeric))) {
				System.err.println("Harus Digit!");
				username = "NOTTRUE";
				continue;
			} else if (vName.contains(username)) {
				System.err.println("Student ID sudah terpakai, silahkan contact administrator");
				username = "NOTTRUE";
				continue;
			}
		} while (username.length() < 10);

		do {
			System.out.print("| PASSWORD (Min. 6 Char)  | : "); password = input.nextLine();
		} while (password.length() < 6);
		do {
			System.out.print("| RE-ENTER PASSWORD       | : "); verifPass = input.nextLine();
			if (!(verifPass.equals(password))) {
				System.err.println("Harus sama dengan password! Coba Lagi");
			}
		} while (!(verifPass.equals(password)));

		System.out.println("\nBERHASIL DIBUAT!!");
		input.nextLine();
		System.out.println("DATA LOGIN");
		System.out.println("Username : " + username);
		System.out.println("Password : " + password);
		vName.add(username);
		vPass.add(password);
		input.nextLine();
		//BACK TO MENU
	}

	public void loginSystem() {
		System.out.println("               -ACCOUNT LOGIN-               ");
		System.out.println("+===========================================+");
		int userLoginLimit = 5;
		int passLoginLimit = 5;
		String userLogin, passLogin;
		do {
			System.out.print("| USERNAME | : "); userLogin = input.nextLine();
			if (!(vName.contains(userLogin))) {
				System.err.println("Username Salah / Sudah Memilih, Sisa Percobaan = "+userLoginLimit);
				System.out.println();
			}
			userLoginLimit--;
			if (userLoginLimit == 0) {
				System.err.println("\nLOGIN GAGAL");
				input.nextLine();
				return;
			}
		} while (!(vName.contains(userLogin)));

		int matchUser = vName.indexOf(userLogin);
		int matchPass = 0;

		do {
			System.out.print("| PASSWORD | : "); passLogin = input.nextLine();
			matchPass = vPass.indexOf(passLogin, matchUser);
			if (!(vPass.contains(passLogin))) {
				System.err.println("Password Salah! Sisa Percobaan = "+passLoginLimit);
				System.out.println();
			}else if (matchUser != matchPass) {
				System.err.println("Password Salah! Sisa Percobaan = "+passLoginLimit);
				System.out.println();
			}
			passLoginLimit--;
			if (passLoginLimit == 0) {
				System.err.println("\nLOGIN GAGAL");
				input.nextLine();
				return;
			}
		} while ((!vPass.contains(passLogin)) || matchUser != matchPass);
		usernameGlobal = userLogin;
		System.out.println("\nBERHASIL LOGIN");
		System.out.println("redirect to halaman voting.....");
		input.nextLine();

		//REDIRECT TO USERPANEL
		userPanel();
	}

	public void adminLogin() {
		System.out.println("                -ADMIN LOGIN-                ");
		System.out.println("+===========================================+");
		int userLoginLimit = 5;
		int passLoginLimit = 5;
		String admin, pass;
		do {
			System.out.print("| USERNAME | : "); admin = input.nextLine();
			if (!(admin.matches(adminUser))) {
				System.err.println("Username Salah! Sisa Percobaan = "+userLoginLimit);
				System.out.println();
			}
			userLoginLimit--;
			if (userLoginLimit == 0) {
				System.err.println("\nLOGIN GAGAL");
				input.nextLine();
				return;
			}
		} while (!(adminUser.matches(admin)));

		do {
			System.out.print("| PASSWORD | : "); pass = input.nextLine();
			if (!(pass.matches(adminPass))) {
				System.err.println("Password Salah! Sisa Percobaan = "+passLoginLimit);
				System.out.println();
			}
			passLoginLimit--;
			if (passLoginLimit == 0) {
				System.err.println("\nLOGIN GAGAL");
				input.nextLine();
				return;
			}

		} while (!(adminPass.matches(pass)));

		System.out.println("\nBERHASIL LOGIN");
		System.out.println("redirect to halaman admin.....");
		input.nextLine();

		//REDIRECT TO USERPANEL
		adminPanel();
	}

	public Main() {
		String menu;
		defaultUser();
		do {
			titleMain();
			System.out.print("Menu Choose : "); menu = input.nextLine();
			switch (menu) {
			case "1":
				hapusScreen();
				registerSystem();
				hapusScreen();
				break;
			case "2":
				hapusScreen();
				loginSystem();
				hapusScreen();
				break;
			case "3":
				hapusScreen();
				adminLogin();
				hapusScreen();
				break;
			case "4":
				System.out.println("\nTERIMA KASIH!");
				System.exit(0);
				break;
			default:
				System.out.println("\nPILIH MENU YANG TERSEDIA");
				hapusScreen();
				input.nextLine();
				break;
			}
		} while (!(menu.equals("4")));
	}

	public static void main(String[] args) {
		new Main();
	}

}