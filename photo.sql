USE [master]
GO
/****** Object:  Database [Photographer]    Script Date: 3/23/2020 12:07:26 AM ******/
CREATE DATABASE [Photographer]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Photographer', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Photographer.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Photographer_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Photographer_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Photographer] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Photographer].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Photographer] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Photographer] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Photographer] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Photographer] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Photographer] SET ARITHABORT OFF 
GO
ALTER DATABASE [Photographer] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Photographer] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Photographer] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Photographer] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Photographer] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Photographer] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Photographer] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Photographer] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Photographer] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Photographer] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Photographer] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Photographer] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Photographer] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Photographer] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Photographer] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Photographer] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Photographer] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Photographer] SET RECOVERY FULL 
GO
ALTER DATABASE [Photographer] SET  MULTI_USER 
GO
ALTER DATABASE [Photographer] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Photographer] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Photographer] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Photographer] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Photographer] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Photographer', N'ON'
GO
USE [Photographer]
GO
/****** Object:  Table [dbo].[Gallery]    Script Date: 3/23/2020 12:07:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Gallery](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[image] [nvarchar](50) NULL,
 CONSTRAINT [PK_Gallery] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ImageGallery]    Script Date: 3/23/2020 12:07:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImageGallery](
	[image] [nvarchar](50) NOT NULL,
	[gallery_id] [int] NOT NULL,
 CONSTRAINT [PK_ImageGallery] PRIMARY KEY CLUSTERED 
(
	[image] ASC,
	[gallery_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Information]    Script Date: 3/23/2020 12:07:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Information](
	[address] [nvarchar](max) NULL,
	[city] [nvarchar](max) NULL,
	[country] [nvarchar](50) NULL,
	[tel] [nvarchar](50) NULL,
	[email] [nvarchar](max) NULL,
	[image] [nvarchar](50) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Intro]    Script Date: 3/23/2020 12:07:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Intro](
	[image] [nvarchar](50) NULL,
	[entry] [nvarchar](max) NULL,
	[aboutme] [nvarchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Share]    Script Date: 3/23/2020 12:07:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Share](
	[icon] [nvarchar](50) NULL,
	[social_network] [nvarchar](50) NOT NULL,
	[url] [nvarchar](max) NULL,
 CONSTRAINT [PK_Share] PRIMARY KEY CLUSTERED 
(
	[social_network] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[Gallery] ([id], [name], [description], [image]) VALUES (1, N'Gallery 1', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'anh1.jpg')
INSERT [dbo].[Gallery] ([id], [name], [description], [image]) VALUES (2, N'Gallery 2', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'anh2.jpg')
INSERT [dbo].[Gallery] ([id], [name], [description], [image]) VALUES (3, N'Gallery 3', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'anh3.jpg')
INSERT [dbo].[Gallery] ([id], [name], [description], [image]) VALUES (4, N'Gallery 4', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'anh3.jpg')
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh0.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh0.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh0.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh0.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh1.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh1.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh1.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh1.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh2.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh2.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh2.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh2.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh3.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh3.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh3.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh3.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh4.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh4.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh4.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh4.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh5.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh5.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh5.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh5.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh6.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh6.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh6.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh6.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh7.jpg', 1)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh7.jpg', 2)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh7.jpg', 3)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh7.jpg', 4)
INSERT [dbo].[ImageGallery] ([image], [gallery_id]) VALUES (N'anh8.jpg', 2)
INSERT [dbo].[Information] ([address], [city], [country], [tel], [email], [image]) VALUES (N'km29 Dai Lo Thang Long - Thach Hoa - Thach That', N'Ha Noi', N'Viet Nam', N'0973820537', N'anhhlthe130274@fpt.edu.vn', N'vt.png')
INSERT [dbo].[Intro] ([image], [entry], [aboutme]) VALUES (N'anh0.jpg', N'Lorem ipsum dolor sit amet', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim')
INSERT [dbo].[Share] ([icon], [social_network], [url]) VALUES (N'fb.png', N'Facebook', N'https://www.facebook.com/')
INSERT [dbo].[Share] ([icon], [social_network], [url]) VALUES (N'gg.png', N'Google+', N'https://www.google.com.vn/')
INSERT [dbo].[Share] ([icon], [social_network], [url]) VALUES (N'tw.png', N'Twitter', N'https://twitter.com/')
ALTER TABLE [dbo].[ImageGallery]  WITH CHECK ADD  CONSTRAINT [FK_ImageGallery_Gallery] FOREIGN KEY([gallery_id])
REFERENCES [dbo].[Gallery] ([id])
GO
ALTER TABLE [dbo].[ImageGallery] CHECK CONSTRAINT [FK_ImageGallery_Gallery]
GO
USE [master]
GO
ALTER DATABASE [Photographer] SET  READ_WRITE 
GO
