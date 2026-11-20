package com.shophub.practice;

public class Day02Practice {
    public static void main(String[] args) {
        System.out.println("===== BÀI THỰC HÀNH NGÀY 2: JAVA CORE & LOGIC =====");

        // Mở chạy lần lượt toàn bộ 8 snippet
        runSnippet1();
        runSnippet2();
        runSnippet3();
        runSnippet4();
        runSnippet5();
        runSnippet6();
        runSnippet7();
        runSnippet8();
    }

    // =========================================================================
    // SNIPPET 1: Khai báo 8 kiểu dữ liệu nguyên thủy & Wrapper Class (ShopHub)
    // =========================================================================
    private static void runSnippet1() {
        System.out.println("\n--- [Snippet 1] Khai báo Kiểu Dữ Liệu Sản Phẩm ShopHub ---");

        // 1. Khóa chính ID: Dùng Wrapper Long (để hỗ trợ giá trị null khi Entity chưa lưu vào DB)
        Long productId = 1001L;

        // 2. Tên sản phẩm: Kiểu tham chiếu String
        String productName = "iPhone 15 Pro Max 256GB";

        // 3. Mã phân loại kho: Kiểu byte (tiết kiệm bộ nhớ, phạm vi -128 đến 127)
        byte warehouseCode = 12;

        // 4. Số tháng bảo hành: Kiểu short (2 bytes)
        short warrantyMonths = 12;

        // 5. Số lượng tồn kho: int (nguyên thủy) và Integer (Wrapper Object)
        int stockQuantity = 45;
        Integer stockWrapper = stockQuantity; // Autoboxing: int -> Integer

        // 6. Lượt xem sản phẩm: Kiểu long (số nguyên lớn 8 bytes, có hậu tố L)
        long totalViews = 1500000L;

        // 7. Điểm đánh giá trung bình: Kiểu float (bắt buộc có hậu tố 'f' hoặc 'F')
        float rating = 4.8f;

        // 8. Đơn giá sản phẩm: Kiểu double (số thực 8 bytes)
        double price = 29990000.0;

        // 9. Ký tự phân hạng chất lượng: Kiểu char (dùng dấu nháy đơn)
        char qualityGrade = 'S';

        // 10. Trạng thái kinh doanh: Kiểu boolean (true/false)
        boolean isAvailable = true;

        // In kết quả tóm tắt sản phẩm
        System.out.println("ID (Long): " + productId);
        System.out.println("Tên (String): " + productName);
        System.out.println("Kho (byte): " + warehouseCode + " | Bảo hành (short): " + warrantyMonths + " tháng");
        System.out.println("Tồn kho (int / Integer): " + stockQuantity + " / " + stockWrapper);
        System.out.println("Lượt xem (long): " + totalViews);
        System.out.println("Đánh giá (float): " + rating + " sao");
        System.out.println("Đơn giá (double): " + price + " VNĐ");
        System.out.println("Phân hạng (char): " + qualityGrade);
        System.out.println("Đang mở bán (boolean): " + isAvailable);
    }

    // =========================================================================
    // SNIPPET 2: Thử nghiệm so sánh String (== vs .equals) & String Pool
    // =========================================================================
    private static void runSnippet2() {
        System.out.println("\n--- [Snippet 2] Thử nghiệm So sánh String (== vs .equals) ---");

        // 1. Khai báo các chuỗi mã giảm giá
        String coupon1 = "SALE50";
        String coupon2 = "SALE50";
        String coupon3 = new String("SALE50");
        String coupon4 = null;

        // --- Thử nghiệm 1: So sánh coupon1 == coupon2 ---
        System.out.println("coupon1 == coupon2: " + (coupon1 == coupon2));
        System.out.println("-> Giải thích: Ra TRUE vì cả 2 cùng trỏ vào 1 ô nhớ trong String Constant Pool.");

        // --- Thử nghiệm 2: So sánh coupon1 == coupon3 ---
        System.out.println("coupon1 == coupon3: " + (coupon1 == coupon3));
        System.out.println("-> Giải thích: Ra FALSE vì coupon3 tạo bằng từ khóa 'new', JVM cấp phát ô nhớ Heap riêng.");

        // --- Thử nghiệm 3: So sánh bằng .equals() ---
        System.out.println("coupon1.equals(coupon3): " + coupon1.equals(coupon3));
        System.out.println("-> Giải thích: Ra TRUE vì .equals() so sánh từng ký tự nội dung bên trong.");

        // --- Thử nghiệm 4: Kỹ thuật chống sập khi so sánh với null ---
        // Cách 1: Yoda Condition (Chuỗi cố định đứng trước)
        System.out.println("\"SALE50\".equals(coupon4): " + "SALE50".equals(coupon4));

        // Cách 2: Dùng java.util.Objects.equals()
        System.out.println("Objects.equals(coupon1, coupon4): " + java.util.Objects.equals(coupon1, coupon4));

        // Minh họa bẫy sập: Nếu gọi coupon4.equals(...) khi coupon4 bị null
        try {
            boolean failResult = coupon4.equals("SALE50");
        } catch (NullPointerException e) {
            System.out.println("Cảnh báo: coupon4.equals(\"SALE50\") ném lỗi -> " + e.getClass().getSimpleName() + " (Đã bắt lỗi thành công)!");
        }
    }

    // =========================================================================
    // SNIPPET 3: Cạm bẫy Autoboxing & Phòng chống NullPointerException (NPE)
    // =========================================================================
    private static void runSnippet3() {
        System.out.println("\n--- [Snippet 3] Cạm bẫy Autoboxing & NullPointerException ---");

        // Nghiệp vụ: Khách hàng mua thêm tháng bảo hành phụ (Client có thể gửi null nếu không mua)
        Integer extraWarrantyMonths = null; // Khách hàng không chọn thêm gói bảo hành
        int pricePerMonth = 50000;          // 50,000 VNĐ / tháng

        // CÁCH AN TOÀN: Dùng toán tử 3 ngôi kiểm tra null trước khi tính toán
        int safeMonths = (extraWarrantyMonths != null) ? extraWarrantyMonths : 0;
        int totalWarrantyFee = safeMonths * pricePerMonth;
        System.out.println("Số tháng bảo hành thêm (an toàn): " + safeMonths + " tháng");
        System.out.println("Phí gia hạn bảo hành: " + totalWarrantyFee + " VNĐ");

        // MINH HỌA CẠM BẪY SẬP HỆ THỐNG:
        // Khi bạn viết: extraWarrantyMonths * pricePerMonth
        // JVM sẽ tự động bóc tách (Unboxing) bằng cách gọi ngầm: extraWarrantyMonths.intValue()
        // Nhưng vì extraWarrantyMonths đang là null -> ném NullPointerException ngay!
        try {
            int crashFee = extraWarrantyMonths * pricePerMonth; // Gây sập ở đây!
        } catch (NullPointerException e) {
            System.out.println("Bẫy unboxing: extraWarrantyMonths * 50000 làm sập hệ thống do " + e.getClass().getSimpleName() + "!");
        }
    }

    // =========================================================================
    // SNIPPET 4: So sánh hiệu năng nối chuỗi (String + vs StringBuilder)
    // =========================================================================
    private static void runSnippet4() {
        System.out.println("\n--- [Snippet 4] Hiệu Năng Nối Chuỗi: String (+) vs StringBuilder ---");

        int loopCount = 5000;

        // CÁCH 1: Dùng toán tử '+' với String bất biến (Immutable)
        // Mỗi lần lặp sẽ sinh thêm 1 Object mới trong Heap, làm chậm và nghẽn Garbage Collector
        long startTime1 = System.currentTimeMillis();
        String reportString = "";
        for (int i = 1; i <= loopCount; i++) {
            reportString += "ORD-" + i + ",";
        }
        long endTime1 = System.currentTimeMillis();
        long stringDuration = endTime1 - startTime1;
        System.out.println("Thời gian dùng String (+): " + stringDuration + " ms (Độ dài: " + reportString.length() + " ký tự)");

        // CÁCH 2: Dùng StringBuilder có thể thay đổi (Mutable)
        // Ghi trực tiếp vào mảng ký tự nội bộ, không tạo ra đối tượng rác trung gian
        long startTime2 = System.currentTimeMillis();
        StringBuilder reportBuilder = new StringBuilder();
        for (int i = 1; i <= loopCount; i++) {
            reportBuilder.append("ORD-").append(i).append(",");
        }
        String finalReport = reportBuilder.toString();
        long endTime2 = System.currentTimeMillis();
        long builderDuration = endTime2 - startTime2;
        System.out.println("Thời gian dùng StringBuilder: " + builderDuration + " ms (Độ dài: " + finalReport.length() + " ký tự)");

        System.out.println("-> KẾT LUẬN: StringBuilder chạy nhanh gấp nhiều lần so với String (+) trong vòng lặp!");
    }

    // =========================================================================
    // SNIPPET 5: Luồng điều khiển if-else & Toán tử ba ngôi (Ternary)
    // =========================================================================
    private static void runSnippet5() {
        System.out.println("\n--- [Snippet 5] Tính Phí Vận Chuyển & Phân Hạng VIP ---");

        double orderTotal = 320000.0; // Tổng giá trị đơn hàng: 320,000 VNĐ
        int distanceKm = 8;           // Khoảng cách giao hàng: 8 km
        int purchaseHistoryCount = 14; // Khách đã mua thành công 14 đơn trước đó

        // 1. Phân hạng khách hàng bằng Toán tử ba ngôi (Ternary Operator)
        String customerTier = (purchaseHistoryCount > 10) ? "VIP" : "STANDARD";

        // 2. Tính phí ship bằng cấu trúc if-else lồng nhau
        double shippingFee;
        if (orderTotal >= 500000.0) {
            shippingFee = 0.0; // Miễn phí vận chuyển (Freeship) cho đơn từ 500k
        } else {
            if (distanceKm <= 5) {
                shippingFee = 15000.0; // Cự ly gần: 15,000 VNĐ
            } else {
                // Cự ly xa: 15k cơ bản + 3k cho mỗi km vượt quá 5km
                shippingFee = 15000.0 + (distanceKm - 5) * 3000.0;
            }
        }

        System.out.println("Giá trị đơn hàng: " + orderTotal + " VNĐ");
        System.out.println("Khoảng cách giao hàng: " + distanceKm + " km");
        System.out.println("Phân hạng khách hàng: " + customerTier);
        System.out.println("Phí vận chuyển phải trả: " + shippingFee + " VNĐ " + (shippingFee == 0 ? "(Freeship)" : ""));
    }

    // =========================================================================
    // SNIPPET 6: Enhanced Switch Expression (Java 17) - Quản lý trạng thái đơn
    // =========================================================================
    private static void runSnippet6() {
        System.out.println("\n--- [Snippet 6] Enhanced Switch Expression (Java 17) ---");

        String[] testStatuses = {"NEW", "SHIPPING", "DELIVERED", "CANCELLED", "UNKNOWN"};

        for (String status : testStatuses) {
            // Enhanced Switch trả về trực tiếp giá trị chuỗi thông báo
            // Dùng cú pháp mũi tên '->', không cần lệnh break, chống lỗi fall-through
            String action = switch (status) {
                case "NEW" -> "Thông báo cho chủ shop chuẩn bị hàng đóng gói.";
                case "PROCESSING" -> "In phiếu giao hàng và điều phối nhân viên lấy hàng.";
                case "SHIPPING" -> "Bật định vị đơn hàng và gửi SMS mã vận đơn cho khách.";
                case "DELIVERED" -> "Cộng điểm tích lũy thành viên và gửi email cảm ơn.";
                case "CANCELLED" -> "Hoàn tiền về ví ShopHub Pay và hoàn số lượng vào kho.";
                default -> "Cảnh báo: Trạng thái không hợp lệ trong hệ thống!";
            };

            System.out.println("Trạng thái [" + status + "] -> Hành động: " + action);
        }
    }

    // =========================================================================
    // SNIPPET 7: Duyệt tồn kho bằng for-each kết hợp continue & phân loại
    // =========================================================================
    private static void runSnippet7() {
        System.out.println("\n--- [Snippet 7] Duyệt & Phân Loại Tồn Kho Bằng for-each ---");

        // Danh sách số lượng tồn kho của các mặt hàng trong kho
        int[] inventoryList = {12, 0, 4, 25, 0, 3, 100, -1, 2};

        System.out.println("Quét danh sách tồn kho:");
        for (int stock : inventoryList) {
            // Nếu dữ liệu âm: Kho bị lỗi số liệu -> Bỏ qua lần lặp này bằng continue
            if (stock < 0) {
                System.out.println("[BỎ QUA] Phát hiện dữ liệu lỗi tồn kho âm: " + stock);
                continue;
            }

            // Phân loại tình trạng hàng hóa
            if (stock == 0) {
                System.out.println("-> [HẾT HÀNG] Tồn kho: 0 chiếc (Cần nhập kho khẩn cấp!)");
            } else if (stock <= 5) {
                System.out.println("-> [CẢNH BÁO SẮP HẾT] Tồn kho thấp: còn " + stock + " chiếc");
            } else {
                System.out.println("-> [ỔN ĐỊNH] Đủ hàng cung ứng: còn " + stock + " chiếc");
            }
        }
    }

    // =========================================================================
    // SNIPPET 8: Vòng lặp do-while - Mô phỏng cơ chế thử lại (Retry) thanh toán
    // =========================================================================
    private static void runSnippet8() {
        System.out.println("\n--- [Snippet 8] Vòng Lặp do-while: Retry Cổng Thanh Toán ---");

        int attempt = 0;
        final int MAX_ATTEMPTS = 3;
        boolean isPaymentSuccess = false;

        // Vòng lặp do-while luôn chạy ít nhất 1 lần đầu tiên trước khi kiểm tra điều kiện lặp
        do {
            attempt++;
            System.out.print("Gọi cổng thanh toán ShopHub Pay (Lần thử " + attempt + "/" + MAX_ATTEMPTS + ")... ");

            // Giả lập: Lần 1 và 2 bị chập chờn mạng, đến lần thứ 3 thì thành công
            if (attempt == 3) {
                isPaymentSuccess = true;
                System.out.println("KẾT QUẢ: GIAO DỊCH THÀNH CÔNG!");
            } else {
                System.out.println("KẾT QUẢ: MẠNG GIÁN ĐOẠN, ĐANG THỬ LẠI...");
            }

        } while (!isPaymentSuccess && attempt < MAX_ATTEMPTS);

        // Đánh giá kết quả cuối cùng sau khi thoát vòng lặp
        if (isPaymentSuccess) {
            System.out.println("=> Đơn hàng đã được thanh toán hoàn tất sau " + attempt + " lần kết nối.");
        } else {
            System.out.println("=> Giao dịch thất bại sau " + MAX_ATTEMPTS + " lần thử. Vui lòng thanh toán lại sau!");
        }
    }
}
