### LDAP (Lightweight Directory Access Protocol):

Ngữ cảnh: Thường được sử dụng trong môi trường doanh nghiệp lớn với hệ thống quản lý người dùng tập trung.
Ví dụ: Một công ty có 10,000 nhân viên sử dụng LDAP để lưu trữ thông tin đăng nhập. Khi nhân viên đăng nhập vào hệ thống
email công ty, Spring Security sẽ xác thực thông qua LDAP.

### Single sign-on (SSO):

Ngữ cảnh: Hữu ích cho hệ thống có nhiều ứng dụng liên quan.
Ví dụ: Một trường đại học có portal sinh viên, hệ thống thư viện và hệ thống đăng ký môn học. Sinh viên chỉ cần đăng
nhập một lần vào portal và có thể truy cập tất cả các hệ thống khác mà không cần đăng nhập lại.

### JAAS LoginModule:

Ngữ cảnh: Khi cần tích hợp xác thực với các hệ thống bảo mật Java có sẵn.
Ví dụ: Một ứng dụng Java EE sử dụng JAAS để xác thực người dùng dựa trên cơ sở dữ liệu riêng của công ty.

### Basic Access Authentication:

Ngữ cảnh: Thích hợp cho API đơn giản hoặc ứng dụng nội bộ không yêu cầu bảo mật cao.
Ví dụ: Một API weather cung cấp dữ liệu thời tiết, sử dụng Basic Auth để giới hạn số lượng request từ mỗi client.

### Digest Access Authentication:

Ngữ cảnh: Khi cần bảo mật cao hơn Basic Auth, nhưng không muốn sử dụng HTTPS.
Ví dụ: Một ứng dụng quản lý tài chính cá nhân sử dụng Digest Auth để bảo vệ thông tin đăng nhập của người dùng.

### Remember-me:

Ngữ cảnh: Websites thương mại điện tử hoặc mạng xã hội, nơi người dùng muốn duy trì đăng nhập lâu dài.
Ví dụ: Facebook sử dụng tính năng này để giữ người dùng đăng nhập ngay cả khi họ đóng trình duyệt và mở lại sau vài
ngày.

### Web Form Authentication:

Ngữ cảnh: Phổ biến trong hầu hết các ứng dụng web.
Ví dụ: Trang đăng nhập của Gmail, nơi người dùng nhập email và mật khẩu vào form.

### Authorization:

Ngữ cảnh: Cần thiết trong mọi ứng dụng có nhiều vai trò người dùng khác nhau.
Ví dụ: Trong hệ thống quản lý bệnh viện, bác sĩ có quyền xem hồ sơ bệnh án, trong khi nhân viên lễ tân chỉ có quyền đặt
lịch hẹn.

### Software Localization:

Ngữ cảnh: Ứng dụng có người dùng từ nhiều quốc gia khác nhau.
Ví dụ: Một ứng dụng du lịch quốc tế hiển thị nội dung bằng ngôn ngữ của người dùng dựa trên location hoặc lựa chọn của
họ.

### HTTP Authorization:

Ngữ cảnh: Kiểm soát quyền truy cập cho các endpoint API hoặc tài nguyên web cụ thể.
Ví dụ: Trong một hệ thống CMS, chỉ admin mới có quyền truy cập các endpoint để xóa bài viết hoặc chỉnh sửa cấu hình hệ
thống.