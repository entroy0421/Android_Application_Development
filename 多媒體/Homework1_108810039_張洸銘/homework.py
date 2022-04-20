import numpy as np
import cv2

image = cv2.imread('2.jpg')
cv2.imshow('image', image)
cv2.imwrite('result/image.jpg', image)
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
cv2.imshow('gray', gray)
cv2.imwrite('result/gray.jpg', gray)

gray = cv2.equalizeHist(gray)
cv2.imshow('equalizehist', gray)
cv2.imwrite('result/equalizehist.jpg', gray)

kernel_size = 5
blur_gray = cv2.GaussianBlur(gray, (kernel_size, kernel_size), 0)
cv2.imshow('blur gray', blur_gray)
cv2.imwrite('result/blur_gray.jpg', blur_gray)

_, threshold_image = cv2.threshold(blur_gray, 200, 255, cv2.THRESH_BINARY)
cv2.imshow('threshold', threshold_image)
cv2.imwrite('result/threshold_image.jpg', threshold_image)

kernel = np.ones((3, 3), np.uint8)
morphology_image = cv2.morphologyEx(threshold_image, cv2.MORPH_OPEN, kernel)
cv2.imshow('mor', morphology_image)
cv2.imwrite('result/morphology_image.jpg', morphology_image)

low_threshold = 100
high_threshold = 200
masked_edges = cv2.Canny(morphology_image, low_threshold, high_threshold)
cv2.imshow('masked', masked_edges)
cv2.imwrite('result/masked_edges.jpg', masked_edges)

rho = 1
theta = np.pi / 180
threshold = 50
min_line_length = 100
max_line_gap = 20

line_image = np.copy(image)

lines = cv2.HoughLinesP(masked_edges, rho, theta, threshold, min_line_length, max_line_gap)

for line in lines:
    x1,y1,x2,y2 = line[0]
    cv2.line(line_image,(x1,y1),(x2,y2),(0,0,255),10)

# color_edges = np.dstack((masked_edges, masked_edges, masked_edges))

# combo = cv2.addWeighted(color_edges, 0.8, line_image, 1, 0) 

cv2.imshow('result', line_image)
cv2.imwrite('result/result.jpg', line_image)
cv2.waitKey(0)
cv2.destroyAllWindows()