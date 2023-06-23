import java.util.*;  //Mendefinisikan impor paket java.util yang berisi kelas-kelas utilitas seperti ArrayList, Queue, dan Scanner.
// memakai 3 metode : Array, Queue, ArrayList

public class RentalMobil { 
    private ArrayList<Pengguna> daftarPengguna; ////Mendeklarasikan variabel daftarPengguna sebagai ArrayList yang berisi objek Pengguna.
    private Queue<Pemesanan> antrianPemesanan; //queue//Mendeklarasikan variabel antrianPemesanan sebagai Queue yang berisi objek Pemesanan.
    private String[] jenisMobilTersedia = {"Toyota", "Honda", "Mitsubishi", "Suzuki", "Nissan"}; //Array :untuk menyimpan jenis mobil yang tersedia
    private String[] idMobilTersedia = {"001", "002", "003", "004", "005"}; //Array: untuk menyimpan id mobil tersedia
    
    public RentalMobil() { //Mendefinisikan konstruktor RentalMobil.
        daftarPengguna = new ArrayList<>(); //arraylist:untuk menyimpan daftar pengguna//memakai add()untuk menambahkan pengguna ke daftar
        antrianPemesanan = new LinkedList<>(); //queue: untuk menyimpan antrian pesanan mobil
    }

    private class Pengguna { //Mendefinisikan kelas inner Pengguna.
        String username; 
        String password;

        public Pengguna(String username, String password) { //Mendefinisikan konstruktor Pengguna dengan parameter username dan password.
            this.username = username;
            this.password = password;
        }
    }

    private class Pemesanan {
        Pengguna pengguna; //Mendeklarasikan variabel pengguna sebagai objek Pengguna.
        String idMobil;
        int tanggalSewa;
        int tanggalKembali;

        public Pemesanan(Pengguna pengguna, String idMobil, int tanggalSewa, int tanggalKembali) { //Mendefinisikan konstruktor Pemesanan dengan parameter pengguna, idMobil, tanggalSewa, dan tanggalKembali.
            this.pengguna = pengguna;
            this.idMobil = idMobil;
            this.tanggalSewa = tanggalSewa;
            this.tanggalKembali = tanggalKembali;
        }
    }

    public void tambahPengguna(String username, String password) { //untuk menmbahkan pengguna baru ke dalam data pengguna
        Pengguna penggunaBaru = new Pengguna(username, password); //Membuat objek Pengguna baru dengan menggunakan konstruktor Pengguna.
        daftarPengguna.add(penggunaBaru); //menambahkan objek Pengguna baru ke dalam daftarPengguna (ArrayList).
    }

    public boolean autentikasiPengguna(String username, String password) { //Mendefinisikan metode autentikasiPengguna dengan parameter username dan password.
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.username.equals(username) && pengguna.password.equals(password)) {
                return true;
            }
        }
        return false;
    } /*baris 44-50 Melakukan iterasi melalui daftarPengguna (ArrayList) dan memeriksa apakah terdapat pengguna dengan username dan password yang sesuai.
        Jika ditemukan, mengembalikan true, jika tidak ditemukan, mengembalikan false.*/

    public void tampilkanMobilTersedia() { //Mendefinisikan metode tampilkanMobilTersedia.
        System.out.println("Jenis Mobil Tersedia:");
        for (int i = 0; i < jenisMobilTersedia.length; i++) {
            System.out.println((i + 1) + ". " + jenisMobilTersedia[i] + " (ID: " + idMobilTersedia[i] + ")");
        }
    } // baris 54-59. Menampilkan daftar mobil yang tersedia beserta ID-nya menggunakan loop for.

    public void pesanMobil(String username, String idMobil, int tanggalSewa, int tanggalKembali) { //Mendefinisikan metode pesanMobil dengan parameter username, idMobil, tanggalSewa, dan tanggalKembali.
        Pengguna pengguna = null;
        for (Pengguna p : daftarPengguna) { 
            if (p.username.equals(username)) {
                pengguna = p;
                break;
            }
        }
        int mobilIndex = -1;
        for (int i = 0; i < idMobilTersedia.length; i++) { //untuk menghitung mobil yang tersedia
            if (idMobilTersedia[i].equals(idMobil)) {
                mobilIndex = i;
                break;
            }
        }
        /*66-73. Mencari objek Pengguna dengan menggunakan username yang diberikan dan mencocokkannya dengan objek Pengguna di dalam daftarPengguna.
        Jika ditemukan, mencari indeks mobil yang sesuai dengan idMobil yang diberikan.
        Jika kedua objek ditemukan, membuat objek Pemesanan baru dan menambahkannya ke dalam antrianPemesanan (Queue). */
        if (pengguna != null && mobilIndex != -1) {
            String jenisMobil = jenisMobilTersedia[mobilIndex];
            Pemesanan pemesanan = new Pemesanan(pengguna, idMobil, tanggalSewa, tanggalKembali);
            antrianPemesanan.offer(pemesanan);
            System.out.println("Pemesanan berhasil dilakukan.");
        } else {
            System.out.println("Pengguna atau pilihan mobil tidak valid.");
        }
    }

    public void tampilkanAntrianPesanan() { //Mendefinisikan metode tampilkanAntrianPesanan.
        if (antrianPemesanan.isEmpty()) {
            System.out.println("Tidak ada pesanan dalam antrian.");
        } else {
            System.out.println("Antrian Pesanan:");
            int nomorPesanan = 1;
            for (Pemesanan pesanan : antrianPemesanan) {
                System.out.println("Nomor Pesanan: " + nomorPesanan);
                System.out.println("Username: " + pesanan.pengguna.username);
                System.out.println("ID Mobil: " + pesanan.idMobil);
                System.out.println("Tanggal Sewa: " + pesanan.tanggalSewa);
                System.out.println("Tanggal Kembali: " + pesanan.tanggalKembali);
                System.out.println("-----------------------------");
                nomorPesanan++;
            }
        }
    }
    /*Memeriksa apakah antrianPemesanan kosong.
    Jika kosong, menampilkan pesan bahwa tidak ada pesanan dalam antrian.
    Jika tidak kosong, menampilkan nomor pesanan, username pengguna, ID mobil, tanggal sewa, dan tanggal kembali untuk setiap objek Pemesanan dalam antrianPemesanan.*/

    public static void main(String[] args) {
        RentalMobil rental = new RentalMobil();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) { //perulangan
            System.out.println("Menu:");
            System.out.println("1. Daftar Pengguna");
            System.out.println("2. Autentikasi Pengguna");
            System.out.println("3. Tampilkan Mobil Tersedia");
            System.out.println("4. Pesan Mobil");
            System.out.println("5. Tampilkan Antrian Pesanan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    rental.tambahPengguna(username, password);
                    System.out.println("Pengguna dengan username " + username + " telah ditambahkan.");
                    break;
                case 2:
                    System.out.print("Username: ");
                    String inputUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String inputPassword = scanner.nextLine();
                    boolean authenticated = rental.autentikasiPengguna(inputUsername, inputPassword);
                    if (authenticated) {
                        System.out.println("Autentikasi berhasil.");
                    } else {
                        System.out.println("Autentikasi gagal.");
                    }
                    break;
                case 3:
                    rental.tampilkanMobilTersedia();
                    break;
                case 4:
                    System.out.print("Username: ");
                    String usernamePemesan = scanner.nextLine();
                    System.out.print("ID Mobil: ");
                    String idMobilPemesan = scanner.nextLine();
                    System.out.print("Tanggal Sewa (DDMMYYYY): ");
                    int tanggalSewa = scanner.nextInt();
                    System.out.print("Tanggal Kembali (DDMMYYYY): ");
                    int tanggalKembali = scanner.nextInt();
                    rental.pesanMobil(usernamePemesan, idMobilPemesan, tanggalSewa, tanggalKembali);
                    break;
                case 5:
                    rental.tampilkanAntrianPesanan();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
            }

            System.out.println();
        }

        scanner.close();
    }
}

/*Metode main sebagai titik masuk utama program. Membuat objek RentalMobil dan Scanner untuk membaca input dari pengguna.
Melakukan loop utama untuk menampilkan menu dan meminta input dari pengguna.
Sesuai dengan pilihan pengguna, memanggil metode-metode yang sesuai pada objek RentalMobil. */