Untuk memenuhi kebutuhan tampilan pemesanan tiket dan check-in mandiri, kita perlu mendesain beberapa tabel yang saling berhubungan di database. Tabel ini akan mencakup data terkait pengguna, tiket, kelas bus, dan metode pembayaran. Berikut adalah desain skema relasi tabel dalam database yang diperlukan:

### 1. **Tabel `users`**:
Menyimpan data pengguna yang melakukan pemesanan tiket.

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_lengkap VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255)
);
```

### 2. **Tabel `kelas`**:
Menyimpan data jenis kelas bus (biasa atau bisnis).

```sql
CREATE TABLE kelas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_kelas VARCHAR(50) NOT NULL
);
```

Data contoh untuk tabel `kelas`:
```sql
INSERT INTO kelas (nama_kelas) VALUES 
('Biasa'),
('Bisnis');
```

### 3. **Tabel `metode_pembayaran`**:
Menyimpan metode pembayaran yang tersedia (QRIS, Kartu Debit, Kartu Kredit).

```sql
CREATE TABLE metode_pembayaran (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_metode VARCHAR(50) NOT NULL
);
```

Data contoh untuk tabel `metode_pembayaran`:
```sql
INSERT INTO metode_pembayaran (nama_metode) VALUES 
('QRIS'),
('Kartu Debit'),
('Kartu Kredit');
```

### 4. **Tabel `tiket`**:
Menyimpan data tiket yang dipesan oleh pengguna, termasuk kota asal, kota tujuan, kelas, metode pembayaran, dan tanggal keberangkatan.

```sql
CREATE TABLE tiket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    kota_awal VARCHAR(100) NOT NULL,
    kota_tujuan VARCHAR(100) NOT NULL,
    kelas_id INT NOT NULL,
    metode_pembayaran_id INT NOT NULL,
    tanggal_keberangkatan DATE NOT NULL,
    kode_tiket VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (kelas_id) REFERENCES kelas(id),
    FOREIGN KEY (metode_pembayaran_id) REFERENCES metode_pembayaran(id)
);
```

### 5. **Tabel `check_in`**:
Menyimpan data check-in mandiri berdasarkan kode tiket dan nama pengguna yang ada di tiket.

```sql
CREATE TABLE check_in (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tiket_id INT NOT NULL,
    nama VARCHAR(255) NOT NULL,
    check_in_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tiket_id) REFERENCES tiket(id)
);
```

### Relasi Antar Tabel:
- **Tabel `tiket`** terhubung dengan **`users`** melalui `user_id`, yang merepresentasikan siapa yang memesan tiket.
- **Tabel `tiket`** terhubung dengan **`kelas`** melalui `kelas_id`, sehingga tiap tiket memiliki informasi kelas (Biasa/Bisnis).
- **Tabel `tiket`** juga terhubung dengan **`metode_pembayaran`** melalui `metode_pembayaran_id`, untuk menyimpan metode pembayaran yang dipilih pengguna.
- **Tabel `check_in`** terhubung dengan **`tiket`** untuk memproses check-in berdasarkan kode tiket.

### Contoh Data Pemesanan Tiket:

```sql
INSERT INTO users (nama_lengkap, email, password) VALUES ('John Doe', 'john@example.com', 'hashedpassword123');

INSERT INTO tiket (user_id, kota_awal, kota_tujuan, kelas_id, metode_pembayaran_id, tanggal_keberangkatan, kode_tiket)
VALUES (1, 'Jakarta', 'Bandung', 1, 2, '2024-10-01', 'TKT123456');
```

### Integrasi dengan JSF:
Pada sisi aplikasi, Anda bisa melakukan query ke database untuk mendapatkan data pengguna, kelas, metode pembayaran, serta menghubungkan proses penyimpanan data tiket dan check-in ke tabel ini.

Sebagai contoh, untuk menyimpan data pemesanan tiket, Anda bisa menambahkan logika pada **`ticketBean`**:

```java
public void pesanTiket() {
    // Simpan tiket ke database
    String sql = "INSERT INTO tiket (user_id, kota_awal, kota_tujuan, kelas_id, metode_pembayaran_id, tanggal_keberangkatan, kode_tiket) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
    // Gunakan PreparedStatement untuk menyimpan data ke database
    // Pastikan bahwa kode_tiket dibuat unik (misalnya menggunakan UUID atau custom logic)
}
```

### Proses Check-In:
Untuk proses check-in mandiri, Anda bisa memverifikasi kode tiket dan nama sesuai dengan data di tabel `tiket`. Jika valid, simpan data check-in ke tabel `check_in`.

```java
public void prosesCheckIn() {
    // Query database untuk memverifikasi kode tiket dan nama pengguna
    String sql = "SELECT id FROM tiket WHERE kode_tiket = ? AND nama = ?";
    // Jika valid, lakukan insert ke tabel check_in
}
```

### Kesimpulan:
Dengan skema database ini, Anda dapat mengelola proses pemesanan tiket dan check-in mandiri dengan lebih terstruktur. Tabel-tabel ini memungkinkan Anda menyimpan informasi terkait tiket, pengguna, kelas bus, dan metode pembayaran, serta memproses check-in berdasarkan kode tiket. Relasi antar tabel juga sudah diatur dengan foreign key untuk menjaga integritas data.

Apakah ada bagian lain yang ingin diperjelas atau disesuaikan lebih lanjut?
